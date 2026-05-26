import { apiClient } from './apiClient';

export type Review = {
    id: string;
    entryId: string;
    dueAt: string;
    result: string | null;
    reviewedAt: string | null;
    createdAt: string;
};

export const getReviewsForEntry = async (topicId: string, entryId: string): Promise<Review[]> => {
    const response = await apiClient(`/api/topics/${topicId}/entries/${entryId}/reviews`);
    return response;
};

export const getDueReviews = async (topicId: string, entryId: string): Promise<Review[]> => {
    const response = await apiClient(`/api/topics/${topicId}/entries/${entryId}/reviews/due`);
    return response;
};

export const createReview = async (topicId: string, entryId: string): Promise<Review> => {
    return apiClient(`/api/topics/${topicId}/entries/${entryId}/reviews`, {
        method: 'POST',
        body: JSON.stringify({}),
    });
};

export const updateReview = async (topicId: string, entryId: string, reviewId: string, result: string | null): Promise<Review> => {
    return apiClient(`/api/topics/${topicId}/entries/${entryId}/reviews/${reviewId}`, {
        method: 'PATCH',
        body: JSON.stringify({ result }),
    });
};

export const deleteReview = async (topicId: string, entryId: string, reviewId: string): Promise<void> => {
    return apiClient(`/api/topics/${topicId}/entries/${entryId}/reviews/${reviewId}`, {
        method: 'DELETE',
    });
};