import React from 'react';
import { useParams, useLocation } from 'react-router-dom';

const TopicPage = () => {
    const { topicId } = useParams<{ topicId: string }>();
    const location = useLocation();
    const topicName = location.state?.topicName || 'Unknown Topic';
    return (
        <div>
            <h2>Topic: {topicName}</h2>
        </div>
    );
};

export default TopicPage;