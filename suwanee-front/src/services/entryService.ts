import { apiClient } from './apiClient';

export type Entry = {
    id: string;
    topicId: string;
    learned: string;
    insights: string;
    questions: string;
    confidence: number;
    createdAt: string;
};

export const getEntries = async (topicId: string): Promise<Entry[]> => {
    const response = await apiClient(`/api/topics/${topicId}/entries`);
    return response;
}

export const postEntry = async (topicId: string, learned: string, insights: string, questions: string, confidence: number): Promise<Entry> => {
    return apiClient(`/api/topics/${topicId}/entries`, {
        method: 'POST',
        body: JSON.stringify({ learned, insights, questions, confidence }),
    });
}

export const updateEntry = async (topicId: string, entryId: string, learned?: string, insights?: string, questions?: string, confidence?: number): Promise<Entry> => {
    return apiClient(`/api/topics/${topicId}/entries/${entryId}`, {
        method: 'PATCH',
        body: JSON.stringify({ learned, insights, questions, confidence }),
    });
}

export const deleteEntry = async (topicId: string, entryId: string): Promise<void> => {
    return apiClient(`/api/topics/${topicId}/entries/${entryId}`, {
        method: 'DELETE',
    });
}