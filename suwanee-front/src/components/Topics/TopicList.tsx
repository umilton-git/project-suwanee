import React from 'react';
import { getTopics, type Topic } from '../../services/topicService';
import TopicForm from './TopicForm';

const TopicList = () => {
    const [topics, setTopics] = React.useState<Topic[]>([]);
    const [loading, setLoading] = React.useState(true);
    const [error, setError] = React.useState<string | null>(null);

    React.useEffect(() => {
        const fetchTopics = async () => {
            setLoading(true);
            try {
                const data = await getTopics();
                setTopics(data);
            } catch (err) {
                setError('Failed to fetch topics');
            } finally {
                setLoading(false);
            }
        };
        fetchTopics();
    }, []);

    const handleTopicCreated = (newTopic: Topic) => {
        setTopics(prev => [...prev, newTopic]);
    };

    return (
        <div>
            <h2>Your Topics</h2>
            <TopicForm onTopicCreated={handleTopicCreated} />
            {loading && <p>Loading topics...</p>}
            {error && <p style={{ color: 'red' }}>{error}</p>}
            {!loading && !error && topics.length === 0 && (
                <p>No topics yet. Create one to get started.</p>
            )}
            {topics.map(topic => (
                <div key={topic.id}>
                    <h3>{topic.name}</h3>
                    <p>{topic.module}</p>
                </div>
            ))}
        </div>
    );
};

export default TopicList;