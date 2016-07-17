package com.databases.erd.erdtool.models;

public class DBMetaColumnInfoModel
{
    private String oldfield_name;
    private String oldfield_desc;
    private String newcolumn_name;
    private String new_alias;
    private boolean key;
    private boolean inc;
    private String alias;
    private String type;
    private int length;
    private String decimal;
    private String key_column;
    private String keyType;

    public void setOldfield_name(String oldfield_name)
    {
        this.oldfield_name = oldfield_name;
    }

    public String getOldfield_name()
    {
        return oldfield_name;
    }

    public void setOldfield_desc(String oldfield_desc)
    {
        this.oldfield_desc = oldfield_desc;
    }

    public String getOldfield_desc()
    {
        return oldfield_desc;
    }

    public void setNewcolumn_name(String newcolumn_name)
    {
        this.newcolumn_name = newcolumn_name;
    }

    public String getNewcolumn_name()
    {
        return newcolumn_name;
    }

    public void setNew_alias(String new_alias)
    {
        this.new_alias = new_alias;
    }

    public String getNew_alias()
    {
        return new_alias;
    }

    public void setKey(boolean key)
    {
        this.key = key;
    }

    public boolean isKey()
    {
        return key;
    }

    public void setInc(boolean inc)
    {
        this.inc = inc;
    }

    public boolean isInc()
    {
        return inc;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public int getLength()
    {
        return length;
    }

    public void setLength(int length)
    {
        this.length = length;
    }

    public String getAlias()
    {
        return alias;
    }

    public void setAlias(String alias)
    {
        this.alias = alias;
    }

    public String getDecimal()
    {
        return decimal;
    }

    public void setDecimal(String decimal)
    {
        this.decimal = decimal;
    }

    public String getKey_column()
    {
        return key_column;
    }

    public void setKey_column(String key_column)
    {
        this.key_column = key_column;
    }

    public String getKeyType()
    {
        return keyType;
    }

    public void setKeyType(String keyType)
    {
        this.keyType = keyType;
    }

}
