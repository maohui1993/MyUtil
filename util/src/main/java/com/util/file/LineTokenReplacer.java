package com.util.file;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Created by hooyee on 2016/5/26.
 * e-mail maohui_dream@outlook.com
 */
public class LineTokenReplacer {

    private final TokenValue[] tokenArray;
    private final String charsetName;

    public LineTokenReplacer(TokenValueSet tokens) {
        this(tokens, null);
    }

    public LineTokenReplacer(TokenValueSet tokens, String charset) {
        final Object[] tmp = tokens.toArray();
        final int length = tmp.length;
        this.tokenArray = new TokenValue[length];
        System.arraycopy(tmp, 0, tokenArray, 0, length);
        this.charsetName = charset;
    }

    public void replace(File inputFile, File outputFile) {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            reader = new BufferedReader(new FileReader(inputFile));
            if(charsetName != null) {
                FileOutputStream outputStream = new FileOutputStream(outputFile);
                Charset charset = Charset.forName(charsetName);
                writer = new BufferedWriter(new OutputStreamWriter(outputStream, charset));
            } else {
                writer = new BufferedWriter(new FileWriter(outputFile));
            }
            String lineContents = null;
            while((lineContents = reader.readLine()) != null) {
                String modifiedLine = replaceLine(lineContents);
                writer.write(modifiedLine);
                writer.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try {
                if(reader != null)
                    reader.close();
                if(writer != null)
                    writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void replace(String inputFileName, String outputFileName) {
        this.replace(new File(inputFileName), new File(outputFileName));
    }

    private String replaceLine(String lineWithTokens) {
        String tokenFreeString = lineWithTokens;

        for (int i = 0; i < tokenArray.length; i++) {
            TokenValue aPair = tokenArray[i];
            tokenFreeString = tokenFreeString.replace(aPair.delimitedToken, aPair.value);
        }
        return tokenFreeString;
    }
}
