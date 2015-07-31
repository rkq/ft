package org.rkq.ft.stock.focus.parse;

import com.svashishtha.dom.DomParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Created by rick on 7/31/15.
 */
public class StockFocusParser {
    static final String ROW_TAG = "stock";
    static final String COLUMN_SORT_ID_TAG = "sortid";
    static final String COLUMN_CODE_TAG = "code";
    static final String COLUMN_NAME_TAG = "name";
    static final String COLUMN_RATE_TAG = "rate";
    static final String COLUMN_CHANGE_TAG = "change";
    static final String COLUMN_CHANGE_RATE_TAG = "changerate";
    static final String COLUMN_SORT_ID_CHANGE_TAG = "sortidchange";
    static final String COLUMN_PRICE_TAG = "price";
    static final String COLUMN_ATTRIBUTE_FONT_TAG = "font";
    static final String ATTRIBUTE_NAME = "color";
    static final String ATTRIBUTE_VALUE_RED = "red";
    static final String ATTRIBUTE_VALUE_GREEN = "green";
    static char SEPARATOR = '\t';
    public static void main(String[] args) {
        Document document = DomParser.parse(System.in);
        Element stocks = document.getDocumentElement();
        NodeList stockList = stocks.getElementsByTagName(ROW_TAG);
        for (int i = 0; i < stockList.getLength(); ++i) {
            Node stock = stockList.item(i);
            StringBuilder rowBuilder = new StringBuilder();
            NodeList columnList = stock.getChildNodes();
            for (int j = 0; j < columnList.getLength(); ++j) {
                Node column = columnList.item(j);
                if (COLUMN_CHANGE_TAG.equals(column.getNodeName()) ||
                        COLUMN_CHANGE_RATE_TAG.equals(column.getNodeName()) ||
                        COLUMN_SORT_ID_CHANGE_TAG.equals(column.getNodeName()) ||
                        COLUMN_PRICE_TAG.equals(column.getNodeName())) {
                } else {
                    rowBuilder.append(column.getTextContent());
                    rowBuilder.append(SEPARATOR);
                }
            }
            rowBuilder.deleteCharAt(rowBuilder.length() - 1);
            System.out.println(rowBuilder.toString());
        }
    }
}
