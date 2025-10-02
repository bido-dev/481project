
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import HomePage from './pages/HomePage.tsx'
import { LoginPage } from './pages/LoginPage.tsx'
import { Register } from './pages/RegisterPage.tsx'
import MursidNavBar from './components/Navbar.tsx'


function App() {

  return (
    <BrowserRouter>
      <MursidNavBar />
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/register" element={ <Register />} />
      </Routes>
    </BrowserRouter>
  )
}

export default App
