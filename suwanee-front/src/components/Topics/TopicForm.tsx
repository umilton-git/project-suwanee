import React from 'react';
import { postTopic, type Topic } from '../../services/topicService';

type TopicFormProps = {
    onTopicCreated: (topic: Topic) => void;
};

const TopicForm = ({ onTopicCreated }: TopicFormProps) => {
    const [name, setName] = React.useState('');
    const [module, setModule] = React.useState('');
    const [loading, setLoading] = React.useState(false);
    const [error, setError] = React.useState<string | null>(null);

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        setLoading(true);
        setError(null);
        try {
            const newTopic = await postTopic(name, module);
            onTopicCreated(newTopic);
            setName('');
            setModule('');
        } catch (err) {
            setError('Failed to create topic. Please try again.');
        } finally {
            setLoading(false);
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <div>
                <label htmlFor="name">Topic Name</label>
                <input
                    id="name"
                    type="text"
                    placeholder="e.g. Spring Core / IoC"
                    value={name}
                    onChange={e => setName(e.target.value)}
                />
            </div>
            <div>
                <label htmlFor="module">Module</label>
                <input
                    id="module"
                    type="text"
                    placeholder="e.g. Fundamentals"
                    value={module}
                    onChange={e => setModule(e.target.value)}
                />
            </div>
            {error && <p style={{ color: 'red' }}>{error}</p>}
            <button type="submit" disabled={loading}>
                {loading ? 'Creating...' : 'Create Topic'}
            </button>
        </form>
    );
};

export default TopicForm;