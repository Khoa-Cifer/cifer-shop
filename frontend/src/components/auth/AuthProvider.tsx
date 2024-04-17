import { jwtDecode } from "jwt-decode"
import { createContext, useContext, useState } from "react"

export const AuthContext = createContext({
    user: null,
    handleLogin: (token) => { },
    handleLogout: () => { }
})

export const AuthProvider = ({ children }) => {
    const [user, setUser] = useState(null)

    const handleLogin = (token) => {
        const decodedToken = jwtDecode(token)
        sessionStorage.setItem("userId", decodedToken.sub)
        sessionStorage.setItem("userRole", decodedToken.roles)
        sessionStorage.setItem("token", token)
        setUser(decodedToken)
    }

    const handleLogout = (token) => {
        sessionStorage.removeItem("userId")
        sessionStorage.removeItem("userRole")
        sessionStorage.removeItem("token")
        setUser(null)
    }

    return (
        <AuthContext.Provider value={{ user, handleLogin, handleLogout }}>
            {children}
        </AuthContext.Provider>
    )
}

export const useAuth = () => {
    return useContext(AuthContext)
}