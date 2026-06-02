import React from 'react';
import { type Review } from '../../services/reviewService';

type ReviewItemProps = {
    review: Review;
};

const ReviewItem = ({ review }: ReviewItemProps) => {
    return (
        <div style={{
            background: 'var(--color-background-secondary)',
            borderRadius: '8px',
            padding: '8px 12px',
            marginBottom: '6px',
            display: 'flex',
            alignItems: 'center',
            justifyContent: 'space-between'
        }}>
            <span style={{ fontSize: '12px', color: 'var(--color-text-secondary)' }}>
                {new Date(review.createdAt).toLocaleDateString()}
            </span>
            <span style={{
                fontSize: '12px',
                fontWeight: 500,
                color: review.result === 'passed' ? '#3b6d11'
                     : review.result === 'failed' ? '#a32d2d'
                     : 'var(--color-text-tertiary)'
            }}>
                {review.result ?? 'pending'}
            </span>
            <span style={{ fontSize: '11px', color: 'var(--color-text-tertiary)' }}>
                due {new Date(review.dueAt).toLocaleDateString()}
            </span>
        </div>
    );
};

export default ReviewItem;