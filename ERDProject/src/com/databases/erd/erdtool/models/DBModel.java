package com.databases.erd.erdtool.models;

import java.util.ArrayList;
import java.util.List;

public class DBModel
{
    private DBModel parent = null;
    private String name = null;
    private List children = new ArrayList();

    public DBModel(DBModel parent, String word)
    {
        this.parent = parent;
        this.name = word;

        if (parent != null)
        {
            parent.children.add(this);
        }
    }

    public DBModel getParent()
    {
        return parent;
    }

    public List getChildren()
    {
        return children;
    }

    public boolean hasChildren()
    {
        return children.size() > 0;
    }

    public void setChildren(List newChildren)
    {
        if (newChildren != null)
            children = new ArrayList(newChildren);
    }

    public String getName()
    {
        return name;
    }
}
