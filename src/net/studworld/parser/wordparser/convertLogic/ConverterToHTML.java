/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.studworld.parser.wordparser.convertLogic;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.poi.hwpf.HWPFDocumentCore;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.converter.WordToHtmlUtils;
import org.w3c.dom.Document;
import net.studworld.parser.wordparser.parseLogic.ParseLogic;


/**
 *
 * @author Ярослав
 */
public class ConverterToHTML {
    private File docFile;
    private File file;

    /**
     *
     * @param docFile
     */
    public ConverterToHTML(File docFile) {
        this.docFile = docFile;
    }

    /**
     *
     * @throws IOException
     * @throws TransformerConfigurationException
     * @throws TransformerException
     * @throws ParserConfigurationException
     */
    public void convert() throws IOException, TransformerConfigurationException, TransformerException, ParserConfigurationException{
        HWPFDocumentCore wordDocument = WordToHtmlUtils.loadDoc(new FileInputStream("E:\\conf\\int.doc"));

        WordToHtmlConverter wordToHtmlConverter =  new InlineImageWordToHtmlConverter(
                DocumentBuilderFactory.newInstance().newDocumentBuilder()
                        .newDocument());
        wordToHtmlConverter.processDocument(wordDocument);
        Document htmlDocument = wordToHtmlConverter.getDocument();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DOMSource domSource = new DOMSource(htmlDocument);
        StreamResult streamResult = new StreamResult(out);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer serializer = tf.newTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.METHOD, "html");
        serializer.transform(domSource, streamResult);
        out.close();
        String result = new String(out.toByteArray());
        ParseLogic pl = new ParseLogic();
        pl.setLol(result);
        pl.parseDoc();
        System.out.println(result);
    

  }    

    /**
     * @return the docFile
     */
    public File getDocFile() {
        return docFile;
    }

    /**
     * @param docFile the docFile to set
     */
    public void setDocFile(File docFile) {
        this.docFile = docFile;
    }

    /**
     * @return the file
     */
    public File getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(File file) {
        this.file = file;
    }
}