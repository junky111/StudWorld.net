package net.studworld.parser.wordparser.convertLogic;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ярослав
 */

    import net.studworld.parser.base.Base64;

import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Picture;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Ярослав
 */
public class InlineImageWordToHtmlConverter extends WordToHtmlConverter {

    /**
     *
     * @param document
     */
    public InlineImageWordToHtmlConverter(Document document) {
        super(document);
    }

    /**
     *
     * @param currentBlock
     * @param inlined
     * @param picture
     */
    @Override
    protected void processImageWithoutPicturesManager(Element currentBlock,
        boolean inlined, Picture picture)
    {
        Element imgNode = currentBlock.getOwnerDocument().createElement("img");
        StringBuilder sb = new StringBuilder();
        sb.append(Base64.getMimeEncoder().encodeToString(picture.getRawContent()));
        sb.insert(0, "data:"+picture.getMimeType()+";base64,");
        imgNode.setAttribute("src", sb.toString());
        currentBlock.appendChild(imgNode);
    }

}

