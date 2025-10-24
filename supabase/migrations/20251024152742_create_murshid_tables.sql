/*
  # Create Murshid Application Database Schema

  ## Overview
  This migration creates the complete database schema for the Murshid educational guidance application.
  The system helps students explore majors, universities, and get advice from graduates.

  ## New Tables Created
  
  ### 1. `student` - Student accounts
    - `student_id` (serial, primary key) - Unique identifier
    - `username` (varchar(50), unique, not null) - Login username
    - `student_name` (text, not null) - Full name
    - `student_email` (text, unique, not null) - Email address
    - `password` (text, not null) - Hashed password
    - `created_at` (timestamptz) - Account creation timestamp

  ### 2. `university` - Universities offering majors
    - `university_id` (serial, primary key) - Unique identifier
    - `university_name` (text, not null) - University name
    - `university_city` (text) - City location
    - `created_at` (timestamptz) - Record creation timestamp

  ### 3. `major` - Academic majors/programs
    - `major_id` (serial, primary key) - Unique identifier
    - `major_name` (text, not null) - Major name
    - `major_overview` (text) - Description of the major
    - `major_category` (text) - Category (e.g., Engineering, Business)
    - `created_at` (timestamptz) - Record creation timestamp

  ### 4. `graduate` - Graduates who can give advice
    - `graduate_id` (serial, primary key) - Unique identifier
    - `graduate_name` (text, not null) - Graduate's name
    - `status` (text) - Current status (employed, studying, etc.)
    - `major_id` (int, foreign key to major) - Major they graduated from
    - `created_at` (timestamptz) - Record creation timestamp

  ### 5. `advice` - Advice content for majors
    - `advice_id` (serial, primary key) - Unique identifier
    - `advice_content` (text, not null) - The advice text
    - `major_id` (int, foreign key to major) - Related major
    - `created_at` (timestamptz) - Record creation timestamp

  ### 6. `student_experience` - Graduate experiences
    - `student_experience_id` (serial, primary key) - Unique identifier
    - `student_experience_content` (text, not null) - Experience text
    - `major_id` (int, foreign key to major) - Related major
    - `graduate_id` (int, foreign key to graduate) - Author
    - `created_at` (timestamptz) - Record creation timestamp

  ### 7. `university_major` - Universities offering majors (many-to-many)
    - `major_id` (int, foreign key)
    - `university_id` (int, foreign key)
    - Primary key: (major_id, university_id)

  ### 8. `student_search_major` - Student major searches (many-to-many)
    - `student_id` (int, foreign key)
    - `major_id` (int, foreign key)
    - Primary key: (student_id, major_id)

  ### 9. `graduate_advice` - Graduates giving advice (many-to-many)
    - `graduate_id` (int, foreign key)
    - `advice_id` (int, foreign key)
    - Primary key: (graduate_id, advice_id)

  ## Security
    - RLS enabled on all tables
    - Public read access for universities, majors, graduates, advice, and experiences
    - Students can only read/update their own records
    - Authentication required for student operations
*/

-- Create student table
CREATE TABLE IF NOT EXISTS student (
  student_id SERIAL PRIMARY KEY,
  username VARCHAR(50) UNIQUE NOT NULL,
  student_name TEXT NOT NULL,
  student_email TEXT UNIQUE NOT NULL,
  password TEXT NOT NULL,
  created_at TIMESTAMPTZ DEFAULT now()
);

-- Create university table
CREATE TABLE IF NOT EXISTS university (
  university_id SERIAL PRIMARY KEY,
  university_name TEXT NOT NULL,
  university_city TEXT,
  created_at TIMESTAMPTZ DEFAULT now()
);

-- Create major table
CREATE TABLE IF NOT EXISTS major (
  major_id SERIAL PRIMARY KEY,
  major_name TEXT NOT NULL,
  major_overview TEXT,
  major_category TEXT,
  created_at TIMESTAMPTZ DEFAULT now()
);

-- Create graduate table
CREATE TABLE IF NOT EXISTS graduate (
  graduate_id SERIAL PRIMARY KEY,
  graduate_name TEXT NOT NULL,
  status TEXT,
  major_id INT NOT NULL REFERENCES major(major_id) ON DELETE CASCADE,
  created_at TIMESTAMPTZ DEFAULT now()
);

-- Create advice table
CREATE TABLE IF NOT EXISTS advice (
  advice_id SERIAL PRIMARY KEY,
  advice_content TEXT NOT NULL,
  major_id INT NOT NULL REFERENCES major(major_id) ON DELETE CASCADE,
  created_at TIMESTAMPTZ DEFAULT now()
);

-- Create student_experience table
CREATE TABLE IF NOT EXISTS student_experience (
  student_experience_id SERIAL PRIMARY KEY,
  student_experience_content TEXT NOT NULL,
  major_id INT NOT NULL REFERENCES major(major_id) ON DELETE CASCADE,
  graduate_id INT NOT NULL REFERENCES graduate(graduate_id) ON DELETE CASCADE,
  created_at TIMESTAMPTZ DEFAULT now()
);

-- Create university_major junction table
CREATE TABLE IF NOT EXISTS university_major (
  major_id INT NOT NULL REFERENCES major(major_id) ON DELETE CASCADE,
  university_id INT NOT NULL REFERENCES university(university_id) ON DELETE CASCADE,
  PRIMARY KEY (major_id, university_id)
);

-- Create student_search_major junction table
CREATE TABLE IF NOT EXISTS student_search_major (
  student_id INT NOT NULL REFERENCES student(student_id) ON DELETE CASCADE,
  major_id INT NOT NULL REFERENCES major(major_id) ON DELETE CASCADE,
  PRIMARY KEY (student_id, major_id)
);

-- Create graduate_advice junction table
CREATE TABLE IF NOT EXISTS graduate_advice (
  graduate_id INT NOT NULL REFERENCES graduate(graduate_id) ON DELETE CASCADE,
  advice_id INT NOT NULL REFERENCES advice(advice_id) ON DELETE CASCADE,
  PRIMARY KEY (graduate_id, advice_id)
);

-- Enable RLS on all tables
ALTER TABLE student ENABLE ROW LEVEL SECURITY;
ALTER TABLE university ENABLE ROW LEVEL SECURITY;
ALTER TABLE major ENABLE ROW LEVEL SECURITY;
ALTER TABLE graduate ENABLE ROW LEVEL SECURITY;
ALTER TABLE advice ENABLE ROW LEVEL SECURITY;
ALTER TABLE student_experience ENABLE ROW LEVEL SECURITY;
ALTER TABLE university_major ENABLE ROW LEVEL SECURITY;
ALTER TABLE student_search_major ENABLE ROW LEVEL SECURITY;
ALTER TABLE graduate_advice ENABLE ROW LEVEL SECURITY;

-- RLS Policies for student table
CREATE POLICY "Students can view their own profile"
  ON student FOR SELECT
  TO authenticated
  USING (student_id = current_setting('app.current_student_id', true)::int);

CREATE POLICY "Anyone can create student account"
  ON student FOR INSERT
  TO anon, authenticated
  WITH CHECK (true);

CREATE POLICY "Students can update their own profile"
  ON student FOR UPDATE
  TO authenticated
  USING (student_id = current_setting('app.current_student_id', true)::int)
  WITH CHECK (student_id = current_setting('app.current_student_id', true)::int);

-- RLS Policies for public read-only tables (university, major, graduate, advice, student_experience)
CREATE POLICY "Anyone can view universities"
  ON university FOR SELECT
  TO anon, authenticated
  USING (true);

CREATE POLICY "Anyone can view majors"
  ON major FOR SELECT
  TO anon, authenticated
  USING (true);

CREATE POLICY "Anyone can view graduates"
  ON graduate FOR SELECT
  TO anon, authenticated
  USING (true);

CREATE POLICY "Anyone can view advice"
  ON advice FOR SELECT
  TO anon, authenticated
  USING (true);

CREATE POLICY "Anyone can view student experiences"
  ON student_experience FOR SELECT
  TO anon, authenticated
  USING (true);

CREATE POLICY "Anyone can view university-major relationships"
  ON university_major FOR SELECT
  TO anon, authenticated
  USING (true);

CREATE POLICY "Anyone can view graduate-advice relationships"
  ON graduate_advice FOR SELECT
  TO anon, authenticated
  USING (true);

-- RLS Policies for student_search_major (students manage their own searches)
CREATE POLICY "Students can view their own searches"
  ON student_search_major FOR SELECT
  TO authenticated
  USING (student_id = current_setting('app.current_student_id', true)::int);

CREATE POLICY "Students can add their own searches"
  ON student_search_major FOR INSERT
  TO authenticated
  WITH CHECK (student_id = current_setting('app.current_student_id', true)::int);

CREATE POLICY "Students can delete their own searches"
  ON student_search_major FOR DELETE
  TO authenticated
  USING (student_id = current_setting('app.current_student_id', true)::int);

-- Create indexes for better query performance
CREATE INDEX IF NOT EXISTS idx_student_username ON student(username);
CREATE INDEX IF NOT EXISTS idx_student_email ON student(student_email);
CREATE INDEX IF NOT EXISTS idx_graduate_major ON graduate(major_id);
CREATE INDEX IF NOT EXISTS idx_advice_major ON advice(major_id);
CREATE INDEX IF NOT EXISTS idx_student_experience_major ON student_experience(major_id);
CREATE INDEX IF NOT EXISTS idx_student_experience_graduate ON student_experience(graduate_id);