const API_URL = 'http://localhost:8080';

export const login = async (email: string, password: string): Promise<string> => {
    const response = await fetch(`${API_URL}/api/auth/login`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ email, password }),
    });

    if (!response.ok) {
        throw new Error('Invalid email or password');
    }

    const data = await response.json();
    return data.token;
};