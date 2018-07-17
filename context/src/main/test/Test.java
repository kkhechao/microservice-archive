

import com.jovezhao.nest.utils.JsonUtils;
import com.zqkh.archive.context.appservice.impl.domain.LocusInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Test
 *
 * @author 东来
 * @create 2018/1/31 0031
 */
public class Test {

    public static void main(String[] args) {

       /* List<LocusInfo> l=new ArrayList<>();
        for(int i=0;i<4;i++){
            LocusInfo locusInfo=new LocusInfo();
            locusInfo.setLocusRsValue("locus"+i);
            locusInfo.setChromosomePosition("chromosomePosition"+i);
            locusInfo.setGene("gene"+i);
            locusInfo.setGeneType("geneType"+i);
            l.add(locusInfo);
        }

        String a= JsonUtils.toJsonString(l);
        System.err.println(a);*/


        char a=Test.byteAsciiToChar(77);
        System.err.println(a);

    }


    /**
     * 方法一：将char 强制转换为byte
     * @param ch
     * @return
     */
    public static byte charToByteAscii(char ch){
        byte byteAscii = (byte)ch;

        return byteAscii;
    }

    /**
     * 同理，ascii转换为char 直接int强制转换为char
     * @param ascii
     * @return
     */
    public static char byteAsciiToChar(int ascii){
        char ch = (char)ascii;
        return ch;
    }
}

