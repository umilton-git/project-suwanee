import React from 'react';
import { postEntry, type Entry } from '../../services/entryService';

type EntryFormProps = {
    topicId: string;
    onEntryCreated: (entry: Entry) => void;
};

const EntryForm = ({ topicId, onEntryCreated }: EntryFormProps) => {
    const [learned, setLearned] = React.useState('');
    const [insights, setInsights] = React.useState('');
    const [questions, setQuestions] = React.useState('');
    const [confidence, setConfidence] = React.useState(3);
    const [loading, setLoading] = React.useState(false);
    const [error, setError] = React.useState<string | null>(null);

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        setLoading(true);
        setError(null);
        try {
            const newEntry = await postEntry(topicId, learned, insights, questions, confidence);
            onEntryCreated(newEntry);
            setLearned('');
            setInsights('');
            setQuestions('');
            setConfidence(3);
        } catch (err) {
            setError('Failed to create entry. Please try again.');
        } finally {
            setLoading(false);
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <div>
                <label htmlFor="learned">What I Learned</label>
                <textarea
                    id="learned"
                    placeholder="e.g. Spring Core / IoC"
                    value={learned}
                    onChange={e => setLearned(e.target.value)}
                />
            </div>
            <div>
                <label htmlFor="insights">Key Insights</label>
                <textarea
                    id="insights"
                    placeholder="e.g. Dependency Injection reduces coupling"
                    value={insights}
                    onChange={e => setInsights(e.target.value)}
                />
            </div>
            <div>
                <label htmlFor="questions">Questions</label>
                <textarea
                    id="questions"
                    placeholder="e.g. How does Spring manage beans?"
                    value={questions}
                    onChange={e => setQuestions(e.target.value)}
                />
            </div>
            <div>
                <label htmlFor="confidence">Confidence: {confidence}/5</label>
                <input
                    id="confidence"
                    type="range"
                    min="1"
                    max="5"
                    value={confidence}
                    onChange={e => setConfidence(parseInt(e.target.value))}
                />
            </div>
            {error && <p style={{ color: 'red' }}>{error}</p>}
            <button type="submit" disabled={loading}>
                {loading ? 'Creating...' : 'Create Entry'}
            </button>
        </form>
    );
};

export default EntryForm;