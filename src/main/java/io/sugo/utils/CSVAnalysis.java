package io.sugo.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.htrace.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.Map;

/**
 * Created by sugo on 16-7-21.
 */
public class CSVAnalysis {



    public static void main(String[] args) throws Throwable {
        String a = ".23";
        System.out.println(Float.parseFloat(a));
        String fieldString = "JYLSH,MXXH,SBXH,DJXH,NSRDJXH,GRSDSSDXM_DM,YZPZZL_DM,ZSPM_DM,SDQJQ,SDQJZ,SKSSQ_Q,SKSSQ_Z,SL_1,YBTSE,SJJNSE,SKSRLY_DM,SKSSSWJG_DM,SPHM,RTKRQ,SFTS,YKJJE,LRRQ,LRR_DM,XGRQ,XGR_DM,SJGSDQ,YWLX_DM,ZGSWSKFJ_DM,SJTB_SJ,ZSSWJG_DM,SRE,YXBZ,SFZJLX_DM,SFZJHM,XM,NSRSBH,ZLSFQQ,LYDM,YXBZ__,BDBZ,FXBZ,ZRRLX_DM,XYBZ,LRRQ_,LRR_DM_,XGRQ_,XGR_DM_,SJGSDQ_,CJXM,LYQD_DM,SWSX_DM,SLSWJG_DM,SJTB_SJ_,SJYXZT,GDSLX_DM,SSDABH,NSRSBH_,NSRMC,KZZTDJLX_DM,DJZCLX_DM,FDDBRXM,FDDBRSFZJLX_DM,SCJYDZ,FDDBRSFZJHM,SCJYDZXZQHSZ_DM,NSRZT_DM,HY_DM,ZCDZ,ZCDZXZQHSZ_DM,JDXZ_DM,DWLSGX_DM,GDGHLX_DM,DJJG_DM,DJRQ,ZZJG_DM,KQCCSZTDJBZ,LRR_DM__,LRRQ__,XGR_DM__,XGRQ__,SJGSDQ__,ZGSWJ_DM,ZGSWSKFJ_DM_,SSGLY_DM,FJMQYBZ,SWDJBLBZ,SJTB_SJ__,NSRBM,YXBZ_,SHXYDM";
        String[] FILE_HEADER = fieldString.split(",");
        CSVFormat format = CSVFormat.DEFAULT.withHeader(FILE_HEADER).withSkipHeaderRecord(false);
        String str = "\"24415070710063148806\",\"1635\",\"24415061510057690772\",\"20124400100004371217\",\"10124401000000694533\",\"0101\",\"BDA0610135\",\"101060100\",\"2015-05-01\",\"2015-05-31\",\"2015-05-01\",\"2015-05-31\",\".03\",\"4.05\",\"0\",\"0107\",\"24401050000\",\"\",\"\",\"\",\"\",\"2015-07-07 15:50:39\",\"24400009999\",\"\",\"\",\"24401050004\",\"A061005011\",\"24401050004\",\"07-1月 -16 11.47.00.011349 上午\",\"24401050000\",\"3635\",\"N\",\"201\",\"4*7111986*51551244\",\",\"2\",\"10124406009900357555\",\"440105455862993\",\"####学\",\"1110\",\"510\",\"XXX\",\"201\",\"广州市，你是找不到的地方\",\"******************\",\"440105\",\"03\",\"8241\",\"广州市，你是找不到的地方\",\"440105\",\"440105008\",\"20\",\"2\",\"24401700000\",\"2003-03-14\",\"455862993\",\"N\",\"24401701059\",\"2000-03-13 10:52:36\",\"24401050220\",\"2015-08-14 11:39:05\",\"24401050004\",\"24401050000\",\"24401050004\",\"24401050024\",\"N\",\"Y\",\"14-8月 -15 11.39.12.681697 上午\",\"455862993\",\"Y\",\"\"";
        System.out.println(str);
        System.out.println(str.split(",").length);
        StringReader reader = new StringReader(str);
        Iterable<CSVRecord> rs = format.parse(reader);
        for (CSVRecord r : rs) {
            Map<String, String> map = r.toMap();

            System.out.println(new ObjectMapper().writeValueAsString(map));
        }



//        try(Reader in = new FileReader("/data1/orgfile")) {
//
//            Iterable<CSVRecord> records = format.parse(in);
//            for (CSVRecord record : records) {
//                System.out.println(record.toString());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }
}

