import React from 'react';
import { Link } from 'react-router-dom';

// Define the structure for your navigation items
interface NavItem {
    name: string;
    href: string;
}

const navItems: NavItem[] = [
    { name: 'Home', href: '/' },
    { name: 'Majors', href: '/majors' },
    { name: 'Careers', href: '/careers' },
    { name: 'Resources', href: '/resources' },
];

const MursidNavBar: React.FC = () => {
    return (
        // The main navigation container (white background, fixed height, subtle border line)
        <nav className="bg-white border-b border-gray-100 shadow-sm">
            <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
                <div className="flex justify-between items-center h-16">
                    
                    {/* 1. Logo Section (Mursid) */}
                    <div className="flex-shrink-0 flex items-center">
                        {/* Placeholder for the blue flag/logo icon */}
                        <div className="w-6 h-6 mr-2 bg-blue-500 rounded-sm flex items-center justify-center text-white text-xs">
                            {/* You might replace this with an actual SVG/Image component later */}
                            🚩
                        </div>
                        {/* Logo Text */}
                        <Link to="/" className="text-xl font-bold text-gray-800">
                            Mursid
                        </Link>
                    </div>

                    {/* 2. Navigation Links and Button */}
                    <div className="flex items-center space-x-6">
                        {/* Navigation Links (Desktop) */}
                        <div className="hidden sm:ml-6 sm:flex sm:space-x-8">
                            {navItems.map((item) => (
                                <Link
                                    key={item.name}
                                    to={item.href}
                                    // Subtle text color and hover effect
                                    className="text-gray-600 hover:text-gray-800 transition duration-150 ease-in-out font-medium py-2"
                                >
                                    {item.name}
                                </Link>
                            ))}
                        </div>

                        {/* Login Button */}
                        <Link
                            to="/login"
                            // Styling matches the light gray, rounded button in the image
                            className="inline-flex items-center px-4 py-2 border border-transparent text-sm font-medium rounded-lg shadow-sm 
                                       text-gray-800 bg-gray-200 hover:bg-gray-300 transition duration-150 ease-in-out"
                        >
                            Login
                        </Link>
                    </div>
                </div>
            </div>
        </nav>
    );
};

export default MursidNavBar; 