import React from 'react';
import { getReviewsForEntry, createReview, type Review } from '../../services/reviewService';
import ReviewItem from './ReviewItem';

type ReviewListProps = {
    topicId: string;
    entryId: string;
};

const ReviewList = ({ topicId, entryId }: ReviewListProps) => {
    const [reviews, setReviews] = React.useState<Review[]>([]);
    const [loading, setLoading] = React.useState(true);
    const [error, setError] = React.useState<string | null>(null);

    React.useEffect(() => {
        const fetchReviews = async () => {
            setLoading(true);
            try {
                const data = await getReviewsForEntry(topicId, entryId);
                setReviews(data);
            } catch (err) {
                setError('Failed to fetch reviews');
            } finally {
                setLoading(false);
            }
        };
        fetchReviews();
    }, [topicId, entryId]);

    const handleReviewCreated = async () => {
        try {
            const newReview = await createReview(topicId, entryId);
            setReviews(prev => [...prev, newReview]);
        } catch (err) {
            setError('Failed to create review');
        }
    };

    return (
        <div>
            {loading && <p>Loading reviews...</p>}
            {error && <p style={{ color: 'red' }}>{error}</p>}
            {reviews.map(review => (
                <ReviewItem key={review.id} review={review} />
            ))}
            <button onClick={handleReviewCreated}>+ log a review</button>
        </div>
    );
};

export default ReviewList;