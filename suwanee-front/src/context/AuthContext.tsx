import React, { createContext, useContext, useState } from 'react';

type AuthContextType = {
    token: string | null;
    setToken: (token: string | null) => void;
    isAuthenticated: boolean;
};

const AuthContext = createContext<AuthContextType | null>(null);

const isTokenExpired = (token: string): boolean => {
    try {
        const payload = JSON.parse(atob(token.split('.')[1]));
        return payload.exp * 1000 < Date.now();
    } catch {
        return true;
    }
};

export const AuthProvider = ({ children }: { children: React.ReactNode }) => {
    const [token, setToken] = useState<string | null>(() => {
        const stored = localStorage.getItem('token');
        if (stored && isTokenExpired(stored)) {
            localStorage.removeItem('token');
            return null;
        }
        return stored;
    });

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