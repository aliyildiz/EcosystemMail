package com.mentornity.ecosystem_mail.CommentNodes;

import java.util.List;

public class ParentObject {

    public String parentName;
    public String parentMessage;
    public List<ChildObject> childObjects;

    public ParentObject(String parentName, String parentMessage, List<ChildObject> childObjects) {
        this.parentName = parentName;
        this.parentMessage = parentMessage;
        this.childObjects = childObjects;
    }
}
