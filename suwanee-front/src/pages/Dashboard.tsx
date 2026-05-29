import React from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import TopicList from '../components/topics/TopicList';

const Dashboard = () => {
    const navigate = useNavigate();
    const { setToken } = useAuth();

    const handleLogout = () => {
        setToken(null);
        navigate('/login');
    };

    return (
        <div>
            <h1>Dashboard</h1>
            <TopicList />
            <button onClick={handleLogout}>Logout</button>
        </div>
    );
};

export default Dashboard;