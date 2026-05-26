import React from 'react';
import { register } from '../../services/authService';
import { useNavigate } from 'react-router-dom';

const RegisterForm = () => {
    const [email, setEmail] = React.useState('');
    const [password, setPassword] = React.useState('');
    const [confirmPassword, setConfirmPassword] = React.useState('');
    const [error, setError] = React.useState<string | null>(null);
    const [loading, setLoading] = React.useState(false);

    const navigate = useNavigate();

    React.useEffect(() => {
    const token = localStorage.getItem('token');
    if (token) {
        navigate('/dashboard');
        }
    }, []);

    const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true);
    setError(null);
    
    try 
    {
        if (password !== confirmPassword) {
        setError("Passwords do not match.");
        setLoading(false);
        return;
}
         await register(email, password);
         navigate('/dashboard');
        } catch (err) {
            setError("Registration failed. Please check your credentials and try again.");
        } finally {
            setLoading(false);
        }
    };

    return (
        <div>
            <h2>Register</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label htmlFor="email">Email</label>
                    <input
                        id="email"
                        type="email"
                        placeholder="Enter your email"
                        value={email}
                        onChange = {(e) => setEmail(e.target.value)}
                    />
                </div>
                <div>
                    <label htmlFor="password">Password</label>
                    <input
                        id="password"
                        type="password"
                        placeholder="Enter your password"
                        value={password}
                        onChange = {(e) => setPassword(e.target.value)}
                    />
                </div>
                <div>
                    <label htmlFor="confirmPassword">Confirm Password</label>
                    <input
                        id="confirmPassword"
                        type="password"
                        placeholder="Confirm your password"
                        value={confirmPassword}
                        onChange = {(e) => setConfirmPassword(e.target.value)}
                    />
                </div>
                {error && <div style={{ color: 'red' }}>{error}</div>}
                <button type="submit" disabled={loading}>
                    {loading ? 'Registering...' : 'Register'}
                </button>
            </form>
        </div>
    );
};

export default RegisterForm;