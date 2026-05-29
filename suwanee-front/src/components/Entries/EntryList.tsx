import React from 'react';
import { getEntries, type Entry } from '../../services/entryService';
import EntryCard from './EntryCard';
import EntryForm from './EntryForm';

type EntryListProps = {
    topicId: string;
};

const EntryList = ({ topicId }: EntryListProps) => {
    const [entries, setEntries] = React.useState<Entry[]>([]);
    const [loading, setLoading] = React.useState(true);
    const [error, setError] = React.useState<string | null>(null);

    React.useEffect(() => {
        const fetchEntries = async () => {
            setLoading(true);
            try {
                const data = await getEntries(topicId);
                setEntries(data);
            } catch (err) {
                setError('Failed to fetch entries');
            } finally {
                setLoading(false);
            }
        };
        fetchEntries();
    }, [topicId]);

    const handleEntryCreated = (newEntry: Entry) => {
        setEntries(prev => [...prev, newEntry]);
    };

    return (
        <div>
            <EntryForm topicId={topicId} onEntryCreated={handleEntryCreated} />
            {loading && <p>Loading entries...</p>}
            {error && <p style={{ color: 'red' }}>{error}</p>}
            {!loading && !error && entries.length === 0 && (
                <p>No entries yet. Add one to get started.</p>
            )}
            {entries.map(entry => (
                <EntryCard key={entry.id} entry={entry} />
            ))}
        </div>
    );
};

export default EntryList;