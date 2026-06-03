import React from 'react';
import { type Entry } from '../../services/entryService';
import ReviewList from '../reviews/ReviewList';

type EntryCardProps = {
    entry: Entry;
};

const EntryCard = ({ entry }: EntryCardProps) => {
    const [expanded, setExpanded] = React.useState(false);

    return (
        <div style={{
            border: '0.5px solid var(--color-border-tertiary)',
            borderRadius: '12px',
            marginBottom: '8px',
            overflow: 'hidden'
        }}>
            <div
                onClick={() => setExpanded(prev => !prev)}
                style={{
                    padding: '12px 16px',
                    display: 'flex',
                    alignItems: 'center',
                    justifyContent: 'space-between',
                    cursor: 'pointer',
                }}
            >
                <div style={{ flex: 1, minWidth: 0 }}>
                    <div style={{
                        fontSize: '13px',
                        fontWeight: 500,
                        whiteSpace: 'nowrap',
                        overflow: 'hidden',
                        textOverflow: 'ellipsis',
                        color: 'var(--color-text-primary)'
                    }}>
                        {entry.learned}
                    </div>
                    <div style={{
                        fontSize: '11px',
                        color: 'var(--color-text-tertiary)',
                        fontFamily: 'var(--font-mono)',
                        marginTop: '2px'
                    }}>
                        {new Date(entry.createdAt).toLocaleDateString()}
                    </div>
                </div>
                <div style={{ display: 'flex', alignItems: 'center', gap: '10px', flexShrink: 0 }}>
                    <div>
                        {[1,2,3,4,5].map(n => (
                            <span key={n} style={{
                                color: n <= entry.confidence ? '#6db33f' : '#c0dd97',
                                fontSize: '12px'
                            }}>★</span>
                        ))}
                    </div>
                    <span style={{
                        fontSize: '14px',
                        color: 'var(--color-text-tertiary)',
                        transform: expanded ? 'rotate(180deg)' : 'rotate(0deg)',
                        transition: 'transform 0.2s',
                        display: 'inline-block'
                    }}>▾</span>
                </div>
            </div>
            {expanded && (
                <div style={{
                    borderTop: '0.5px solid var(--color-border-tertiary)',
                    padding: '14px 16px'
                }}>
                    <div style={{ fontSize: '10px', fontWeight: 500, textTransform: 'uppercase', letterSpacing: '0.07em', color: 'var(--color-text-tertiary)', marginBottom: '4px' }}>
                        what i learned
                    </div>
                    <div style={{ fontSize: '13px', color: 'var(--color-text-primary)', lineHeight: 1.6, marginBottom: '12px' }}>
                        {entry.learned}
                    </div>
                    {entry.insights && (
                        <>
                            <div style={{ fontSize: '10px', fontWeight: 500, textTransform: 'uppercase', letterSpacing: '0.07em', color: 'var(--color-text-tertiary)', marginBottom: '4px' }}>
                                key insights
                            </div>
                            <div style={{ fontSize: '13px', color: 'var(--color-text-primary)', lineHeight: 1.6, marginBottom: '12px' }}>
                                {entry.insights}
                            </div>
                        </>
                    )}
                    {entry.questions && (
                        <>
                            <div style={{ fontSize: '10px', fontWeight: 500, textTransform: 'uppercase', letterSpacing: '0.07em', color: 'var(--color-text-tertiary)', marginBottom: '4px' }}>
                                open questions
                            </div>
                            <div style={{ fontSize: '13px', color: '#185fa5', lineHeight: 1.6 }}>
                                {entry.questions}
                            </div>
                        </>
                    )}
                </div>
            )}
        </div>
    );
};

export default EntryCard;