import { useParams, useLocation } from 'react-router-dom';
import EntryList from '../components/entries/EntryList';

const TopicPage = () => {
    const { topicId } = useParams<{ topicId: string }>();
    const location = useLocation();
    const topicName = location.state?.topicName || 'Unknown Topic';
    return (
        <div>
            <h2>Topic: {topicName}</h2>
            {topicId ? <EntryList topicId={topicId} /> : <p>Invalid topic ID</p>}
        </div>
    );
};

export default TopicPage;