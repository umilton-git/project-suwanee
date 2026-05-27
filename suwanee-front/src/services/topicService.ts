import { apiClient } from './apiClient';
import type { Entry } from './entryService';

export type Topic = {
    id: string;
    userId: string;
    name: string;
    module: string;
    entries: Entry[];
};

export const getTopics = async (): Promise<Topic[]> => {
    const response = await apiClient('/api/topics');
    return response;
};

export const postTopic = async (name: string, module: string): Promise<Topic> => {
    return apiClient('/api/topics', {
        method: 'POST',
        body: JSON.stringify({ name, module }),
    });
};

export const updateTopic = async (topicId: string, name?: string, module?: string): Promise<Topic> => {
    return apiClient(`/api/topics/${topicId}`, {
        method: 'PATCH',
        body: JSON.stringify({ name, module }),
    });
};

export const deleteTopic = async (topicId: string): Promise<void> => {
    return apiClient(`/api/topics/${topicId}`, {
        method: 'DELETE',
    });
};