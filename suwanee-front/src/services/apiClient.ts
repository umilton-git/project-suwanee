const API_URL = 'http://localhost:8080';

export const apiClient = async (
    endpoint: string,
    options: RequestInit = {}
): Promise<any> => {
    const token = localStorage.getItem('token');

    const response = await fetch(`${API_URL}${endpoint}`, {
        ...options,
        headers: {
            'Content-Type': 'application/json',
            ...(token && { Authorization: `Bearer ${token}` }),
            ...options.headers,
        },
    });

    if (response.status === 401 || response.status === 403) {
        localStorage.removeItem('token');
        window.dispatchEvent(new Event('auth:logout'));
        throw new Error('Session expired');
    }

    if (!response.ok) {
        const error = await response.json();
        throw new Error(error.message || 'Something went wrong');
    }

    if (response.status === 204) {
        return null;
    }

    return response.json();
};

export default apiClient;