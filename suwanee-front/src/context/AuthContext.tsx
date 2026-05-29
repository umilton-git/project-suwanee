import React, { createContext, useContext, useState } from 'react';

type AuthContextType = {
    token: string | null;
    setToken: (token: string | null) => void;
    isAuthenticated: boolean;
};

const AuthContext = createContext<AuthContextType | null>(null);


export const AuthProvider = ({ children }: { children: React.ReactNode }) => {
    const [token, setToken] = useState<string | null>(
        localStorage.getItem('token')
    );

    const handleSetToken = (newToken: string | null) => {
        if (newToken) {
            localStorage.setItem('token', newToken);
        } else {
            localStorage.removeItem('token');
        }
        setToken(newToken);
    };

    React.useEffect(() => {
        const handleLogout = () => {
            setToken(null);
            window.location.href = '/login';
        };
        window.addEventListener('auth:logout', handleLogout);
        return () => window.removeEventListener('auth:logout', handleLogout);
    }, []); 

    return (
        <AuthContext.Provider value={{
            token,
            setToken: handleSetToken,
            isAuthenticated: !!token
        }}>
            {children}
        </AuthContext.Provider>
    );
};

export const useAuth = () => {
    const context = useContext(AuthContext);
    if (!context) throw new Error('useAuth must be used within AuthProvider');
    return context;
};