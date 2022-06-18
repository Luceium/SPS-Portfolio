package com.google.sps.data;

public final class Comment {
    public final long id;
    public final String comment;
    public final long timestamp;

    public Comment (long id, String comment, long timestamp){
        this.id = id;
        this.comment = comment;
        this.timestamp = timestamp;
    }
}