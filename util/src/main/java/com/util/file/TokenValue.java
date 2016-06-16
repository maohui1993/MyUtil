package com.util.file;

/**
 * Created by hooyee on 2016/5/26.
 * e-mail maohui_dream@outlook.com
 */
public final class TokenValue implements Comparable {

    public final String token;
    public final String value;
    public final String delimiter;
    public final String delimitedToken;

    public static final String DEFAULT_DELIMITER = "%%%";

    public TokenValue(String token, String value) {
        this(token, value, DEFAULT_DELIMITER);
    }

    public TokenValue(String token, String value, String delimiter) {
        if(token == null || value == null || delimiter == null) {
            throw new IllegalArgumentException("Null Argument");
        }
        this.token = token;
        this.value = escapeBackslashes(value);
        this.delimiter = delimiter;
        this.delimitedToken = delimiter + token + delimiter;
    }

    public TokenValue(TokenValue other) {
        this.token = other.token;
        this.value = other.value;
        this.delimiter = other.delimiter;
        this.delimitedToken = other.delimitedToken;
    }

    @Override
    public int compareTo(Object other) {
        final TokenValue otherTokenValue = (TokenValue) other;
        return this.token.compareTo(otherTokenValue.token);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TokenValue that = (TokenValue) o;

        if (token != null ? !token.equals(that.token) : that.token != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;
        return !(delimiter != null ? !delimiter.equals(that.delimiter) : that.delimiter != null);

    }

    @Override
    public int hashCode() {
        int result = token != null ? token.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (delimiter != null ? delimiter.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return delimiter + token + delimiter + "=" + value;
    }

    /**
     * 为字符串中的'\'再加上一个'\'，以到达原有效果
     * @param anyString
     * @return
     */
    private String escapeBackslashes(String anyString) {
        final char BACK_SLASH = '\\';
        final StringBuffer escaped = new StringBuffer();
        for(int i=0; i<anyString.length(); i++) {
            final char ch = anyString.charAt(i);
            escaped.append(ch);
            if (ch == BACK_SLASH) {
                escaped.append(BACK_SLASH);
            }
        }
        return escaped.toString();
    }
}
