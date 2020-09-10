package com.zippyttech.downloadsvg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.larvalabs.svgandroid.SVG;
import com.larvalabs.svgandroid.SVGParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ImageView iv;
    private EditText et;
    private Drawable svgDrawable;
    private List<CountriesModel> listCountries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = findViewById(R.id.iv_image);
        et = findViewById(R.id.et_url);
        listCountries = new ArrayList<>();
        // Create a new ImageView
         ImageView imageView = new ImageView(this);
         // Set the background color to white
        imageView.setBackgroundColor(Color.WHITE);
        // Parse the SVG file from the resource
        SVG svg = SVGParser.getSVGFromResource(getResources(), R.raw.android);
        // Get a drawable from the parsed SVG and set it as the drawable for the ImageView
        imageView.setImageDrawable(svg.createPictureDrawable());
        svgDrawable = svg.createPictureDrawable();
        // Set the ImageView as the content view for the Activity setContentView(imageView);

        //iv.setImageDrawable(svgDrawable);

        listCountries.addAll(getList());
        downloadImageSVG("https://restcountries.eu/data/afg.svg");

    }

    private ImageView createImageView(){

        ImageView imageView = new ImageView(this);
        imageView.setBackgroundColor(Color.WHITE);
        SVG svg = SVGParser.getSVGFromResource(getResources(), R.raw.android);
        imageView.setImageDrawable(svg.createPictureDrawable());
        svgDrawable = svg.createPictureDrawable();
        return imageView;
    }

    public void getData(View view) {
        String text = et.getText().toString();
        if (!text.isEmpty())
            downloadImageSVG(text);
        else{
            String err = "No Hay \"imagen url\" para desgargar...";
            Toast.makeText(this, ""+err, Toast.LENGTH_SHORT).show();
            et.setError(err);
        }

    }

    private void downloadImageSVG(@NonNull String url){
            new HttpImageRequestTask(this, ""+url)
                    .setTaskComplete(resp -> {
                Bitmap bitmap = ImageUtils.base64ToBitmap(resp);
                iv.setImageBitmap(bitmap);
            });

    }

    private List<CountriesModel> getList(){
        List<CountriesModel> list = new ArrayList<>();
        String data = "[\n" +
                "            {\"alpha2Code\":\"AF\",\"code\":\"+93\",\"icon\":\"https://restcountries.eu/data/afg.svg\",\"id\":0,\"name\":\"Afghanistan\"},\n" +
                "            {\"alpha2Code\":\"AL\",\"code\":\"+355\",\"icon\":\"https://restcountries.eu/data/alb.svg\",\"id\":2,\"name\":\"Albania\"},\n" +
                "            {\"alpha2Code\":\"DZ\",\"code\":\"+213\",\"icon\":\"https://restcountries.eu/data/dza.svg\",\"id\":3,\"name\":\"Algeria\"},\n" +
                "            {\"alpha2Code\":\"AS\",\"code\":\"+1684\",\"icon\":\"https://restcountries.eu/data/asm.svg\",\"id\":4,\"name\":\"American Samoa\"},\n" +
                "            {\"alpha2Code\":\"AD\",\"code\":\"+376\",\"icon\":\"https://restcountries.eu/data/and.svg\",\"id\":5,\"name\":\"Andorra\"},\n" +
                "            {\"alpha2Code\":\"AO\",\"code\":\"+244\",\"icon\":\"https://restcountries.eu/data/ago.svg\",\"id\":6,\"name\":\"Angola\"},\n" +
                "            {\"alpha2Code\":\"AI\",\"code\":\"+1264\",\"icon\":\"https://restcountries.eu/data/aia.svg\",\"id\":7,\"name\":\"Anguilla\"},\n" +
                "            {\"alpha2Code\":\"AQ\",\"code\":\"+672\",\"icon\":\"https://restcountries.eu/data/ata.svg\",\"id\":8,\"name\":\"Antarctica\"},\n" +
                "            {\"alpha2Code\":\"AG\",\"code\":\"+1268\",\"icon\":\"https://restcountries.eu/data/atg.svg\",\"id\":9,\"name\":\"Antigua and Barbuda\"},\n" +
                "            {\"alpha2Code\":\"AR\",\"code\":\"+54\",\"icon\":\"https://restcountries.eu/data/arg.svg\",\"id\":10,\"name\":\"Argentina\"},\n" +
                "            {\"alpha2Code\":\"AM\",\"code\":\"+374\",\"icon\":\"https://restcountries.eu/data/arm.svg\",\"id\":11,\"name\":\"Armenia\"},\n" +
                "            {\"alpha2Code\":\"AW\",\"code\":\"+297\",\"icon\":\"https://restcountries.eu/data/abw.svg\",\"id\":12,\"name\":\"Aruba\"},\n" +
                "            {\"alpha2Code\":\"AU\",\"code\":\"+61\",\"icon\":\"https://restcountries.eu/data/aus.svg\",\"id\":13,\"name\":\"Australia\"},\n" +
                "            {\"alpha2Code\":\"AT\",\"code\":\"+43\",\"icon\":\"https://restcountries.eu/data/aut.svg\",\"id\":14,\"name\":\"Austria\"},\n" +
                "            {\"alpha2Code\":\"AZ\",\"code\":\"+994\",\"icon\":\"https://restcountries.eu/data/aze.svg\",\"id\":15,\"name\":\"Azerbaijan\"},\n" +
                "            {\"alpha2Code\":\"BS\",\"code\":\"+1242\",\"icon\":\"https://restcountries.eu/data/bhs.svg\",\"id\":16,\"name\":\"Bahamas\"},\n" +
                "            {\"alpha2Code\":\"BH\",\"code\":\"+973\",\"icon\":\"https://restcountries.eu/data/bhr.svg\",\"id\":17,\"name\":\"Bahrain\"},\n" +
                "            {\"alpha2Code\":\"BD\",\"code\":\"+880\",\"icon\":\"https://restcountries.eu/data/bgd.svg\",\"id\":18,\"name\":\"Bangladesh\"},\n" +
                "            {\"alpha2Code\":\"BB\",\"code\":\"+1246\",\"icon\":\"https://restcountries.eu/data/brb.svg\",\"id\":19,\"name\":\"Barbados\"},\n" +
                "            {\"alpha2Code\":\"BY\",\"code\":\"+375\",\"icon\":\"https://restcountries.eu/data/blr.svg\",\"id\":20,\"name\":\"Belarus\"},\n" +
                "            {\"alpha2Code\":\"BE\",\"code\":\"+32\",\"icon\":\"https://restcountries.eu/data/bel.svg\",\"id\":21,\"name\":\"Belgium\"},\n" +
                "            {\"alpha2Code\":\"BZ\",\"code\":\"+501\",\"icon\":\"https://restcountries.eu/data/blz.svg\",\"id\":22,\"name\":\"Belize\"},\n" +
                "            {\"alpha2Code\":\"BJ\",\"code\":\"+229\",\"icon\":\"https://restcountries.eu/data/ben.svg\",\"id\":23,\"name\":\"Benin\"},\n" +
                "            {\"alpha2Code\":\"BM\",\"code\":\"+1441\",\"icon\":\"https://restcountries.eu/data/bmu.svg\",\"id\":24,\"name\":\"Bermuda\"},\n" +
                "            {\"alpha2Code\":\"BT\",\"code\":\"+975\",\"icon\":\"https://restcountries.eu/data/btn.svg\",\"id\":25,\"name\":\"Bhutan\"},\n" +
                "            {\"alpha2Code\":\"BO\",\"code\":\"+591\",\"icon\":\"https://restcountries.eu/data/bol.svg\",\"id\":26,\"name\":\"Bolivia (Plurinational State of)\"},\n" +
                "            {\"alpha2Code\":\"BQ\",\"code\":\"+5997\",\"icon\":\"https://restcountries.eu/data/bes.svg\",\"id\":27,\"name\":\"Bonaire, Sint Eustatius and Saba\"},\n" +
                "            {\"alpha2Code\":\"BA\",\"code\":\"+387\",\"icon\":\"https://restcountries.eu/data/bih.svg\",\"id\":28,\"name\":\"Bosnia and Herzegovina\"},\n" +
                "            {\"alpha2Code\":\"BW\",\"code\":\"+267\",\"icon\":\"https://restcountries.eu/data/bwa.svg\",\"id\":29,\"name\":\"Botswana\"},\n" +
                "            {\"alpha2Code\":\"BV\",\"code\":\"\",\"icon\":\"https://restcountries.eu/data/bvt.svg\",\"id\":30,\"name\":\"Bouvet Island\"},\n" +
                "            {\"alpha2Code\":\"BR\",\"code\":\"+55\",\"icon\":\"https://restcountries.eu/data/bra.svg\",\"id\":31,\"name\":\"Brazil\"},\n" +
                "            {\"alpha2Code\":\"IO\",\"code\":\"+246\",\"icon\":\"https://restcountries.eu/data/iot.svg\",\"id\":32,\"name\":\"British Indian Ocean Territory\"},\n" +
                "            {\"alpha2Code\":\"BN\",\"code\":\"+673\",\"icon\":\"https://restcountries.eu/data/brn.svg\",\"id\":36,\"name\":\"Brunei Darussalam\"},\n" +
                "            {\"alpha2Code\":\"BG\",\"code\":\"+359\",\"icon\":\"https://restcountries.eu/data/bgr.svg\",\"id\":37,\"name\":\"Bulgaria\"},\n" +
                "            {\"alpha2Code\":\"BF\",\"code\":\"+226\",\"icon\":\"https://restcountries.eu/data/bfa.svg\",\"id\":38,\"name\":\"Burkina Faso\"},\n" +
                "            {\"alpha2Code\":\"BI\",\"code\":\"+257\",\"icon\":\"https://restcountries.eu/data/bdi.svg\",\"id\":39,\"name\":\"Burundi\"},\n" +
                "            {\"alpha2Code\":\"CV\",\"code\":\"+238\",\"icon\":\"https://restcountries.eu/data/cpv.svg\",\"id\":43,\"name\":\"Cabo Verde\"},\n" +
                "            {\"alpha2Code\":\"KH\",\"code\":\"+855\",\"icon\":\"https://restcountries.eu/data/khm.svg\",\"id\":40,\"name\":\"Cambodia\"},\n" +
                "            {\"alpha2Code\":\"CM\",\"code\":\"+237\",\"icon\":\"https://restcountries.eu/data/cmr.svg\",\"id\":41,\"name\":\"Cameroon\"},\n" +
                "            {\"alpha2Code\":\"CA\",\"code\":\"+1\",\"icon\":\"https://restcountries.eu/data/can.svg\",\"id\":42,\"name\":\"Canada\"},\n" +
                "            {\"alpha2Code\":\"KY\",\"code\":\"+1345\",\"icon\":\"https://restcountries.eu/data/cym.svg\",\"id\":44,\"name\":\"Cayman Islands\"},\n" +
                "            {\"alpha2Code\":\"CF\",\"code\":\"+236\",\"icon\":\"https://restcountries.eu/data/caf.svg\",\"id\":45,\"name\":\"Central African Republic\"},\n" +
                "            {\"alpha2Code\":\"TD\",\"code\":\"+235\",\"icon\":\"https://restcountries.eu/data/tcd.svg\",\"id\":46,\"name\":\"Chad\"},\n" +
                "            {\"alpha2Code\":\"CL\",\"code\":\"+56\",\"icon\":\"https://restcountries.eu/data/chl.svg\",\"id\":47,\"name\":\"Chile\"},\n" +
                "            {\"alpha2Code\":\"CN\",\"code\":\"+86\",\"icon\":\"https://restcountries.eu/data/chn.svg\",\"id\":48,\"name\":\"China\"},\n" +
                "            {\"alpha2Code\":\"CX\",\"code\":\"+61\",\"icon\":\"https://restcountries.eu/data/cxr.svg\",\"id\":49,\"name\":\"Christmas Island\"},\n" +
                "            {\"alpha2Code\":\"CC\",\"code\":\"+61\",\"icon\":\"https://restcountries.eu/data/cck.svg\",\"id\":50,\"name\":\"Cocos (Keeling) Islands\"},\n" +
                "            {\"alpha2Code\":\"CO\",\"code\":\"+57\",\"icon\":\"https://restcountries.eu/data/col.svg\",\"id\":51,\"name\":\"Colombia\"},\n" +
                "            {\"alpha2Code\":\"KM\",\"code\":\"+269\",\"icon\":\"https://restcountries.eu/data/com.svg\",\"id\":52,\"name\":\"Comoros\"},\n" +
                "            {\"alpha2Code\":\"CG\",\"code\":\"+242\",\"icon\":\"https://restcountries.eu/data/cog.svg\",\"id\":53,\"name\":\"Congo\"},\n" +
                "            {\"alpha2Code\":\"CD\",\"code\":\"+243\",\"icon\":\"https://restcountries.eu/data/cod.svg\",\"id\":54,\"name\":\"Congo (Democratic Republic of the)\"},\n" +
                "            {\"alpha2Code\":\"CK\",\"code\":\"+682\",\"icon\":\"https://restcountries.eu/data/cok.svg\",\"id\":55,\"name\":\"Cook Islands\"},\n" +
                "            {\"alpha2Code\":\"CR\",\"code\":\"+506\",\"icon\":\"https://restcountries.eu/data/cri.svg\",\"id\":56,\"name\":\"Costa Rica\"},\n" +
                "            {\"alpha2Code\":\"HR\",\"code\":\"+385\",\"icon\":\"https://restcountries.eu/data/hrv.svg\",\"id\":57,\"name\":\"Croatia\"},\n" +
                "            {\"alpha2Code\":\"CU\",\"code\":\"+53\",\"icon\":\"https://restcountries.eu/data/cub.svg\",\"id\":58,\"name\":\"Cuba\"},\n" +
                "            {\"alpha2Code\":\"CW\",\"code\":\"+599\",\"icon\":\"https://restcountries.eu/data/cuw.svg\",\"id\":59,\"name\":\"Curaçao\"},\n" +
                "            {\"alpha2Code\":\"CY\",\"code\":\"+357\",\"icon\":\"https://restcountries.eu/data/cyp.svg\",\"id\":60,\"name\":\"Cyprus\"},\n" +
                "            {\"alpha2Code\":\"CZ\",\"code\":\"+420\",\"icon\":\"https://restcountries.eu/data/cze.svg\",\"id\":61,\"name\":\"Czech Republic\"},\n" +
                "            {\"alpha2Code\":\"CI\",\"code\":\"+225\",\"icon\":\"https://restcountries.eu/data/civ.svg\",\"id\":106,\"name\":\"Côte d\\\\u0027Ivoire\"},\n" +
                "            {\"alpha2Code\":\"DK\",\"code\":\"+45\",\"icon\":\"https://restcountries.eu/data/dnk.svg\",\"id\":62,\"name\":\"Denmark\"},\n" +
                "            {\"alpha2Code\":\"DJ\",\"code\":\"+253\",\"icon\":\"https://restcountries.eu/data/dji.svg\",\"id\":63,\"name\":\"Djibouti\"},\n" +
                "            {\"alpha2Code\":\"DM\",\"code\":\"+1767\",\"icon\":\"https://restcountries.eu/data/dma.svg\",\"id\":64,\"name\":\"Dominica\"},\n" +
                "            {\"alpha2Code\":\"DO\",\"code\":\"+1809\",\"icon\":\"https://restcountries.eu/data/dom.svg\",\"id\":65,\"name\":\"Dominican Republic\"},\n" +
                "            {\"alpha2Code\":\"EC\",\"code\":\"+593\",\"icon\":\"https://restcountries.eu/data/ecu.svg\",\"id\":66,\"name\":\"Ecuador\"},\n" +
                "            {\"alpha2Code\":\"EG\",\"code\":\"+20\",\"icon\":\"https://restcountries.eu/data/egy.svg\",\"id\":67,\"name\":\"Egypt\"},\n" +
                "            {\"alpha2Code\":\"SV\",\"code\":\"+503\",\"icon\":\"https://restcountries.eu/data/slv.svg\",\"id\":68,\"name\":\"El Salvador\"},\n" +
                "            {\"alpha2Code\":\"GQ\",\"code\":\"+240\",\"icon\":\"https://restcountries.eu/data/gnq.svg\",\"id\":69,\"name\":\"Equatorial Guinea\"},\n" +
                "            {\"alpha2Code\":\"ER\",\"code\":\"+291\",\"icon\":\"https://restcountries.eu/data/eri.svg\",\"id\":70,\"name\":\"Eritrea\"},\n" +
                "            {\"alpha2Code\":\"EE\",\"code\":\"+372\",\"icon\":\"https://restcountries.eu/data/est.svg\",\"id\":71,\"name\":\"Estonia\"},\n" +
                "            {\"alpha2Code\":\"ET\",\"code\":\"+251\",\"icon\":\"https://restcountries.eu/data/eth.svg\",\"id\":72,\"name\":\"Ethiopia\"},\n" +
                "            {\"alpha2Code\":\"FK\",\"code\":\"+500\",\"icon\":\"https://restcountries.eu/data/flk.svg\",\"id\":73,\"name\":\"Falkland Islands (Malvinas)\"},\n" +
                "            {\"alpha2Code\":\"FO\",\"code\":\"+298\",\"icon\":\"https://restcountries.eu/data/fro.svg\",\"id\":74,\"name\":\"Faroe Islands\"},\n" +
                "            {\"alpha2Code\":\"FJ\",\"code\":\"+679\",\"icon\":\"https://restcountries.eu/data/fji.svg\",\"id\":75,\"name\":\"Fiji\"},\n" +
                "            {\"alpha2Code\":\"FI\",\"code\":\"+358\",\"icon\":\"https://restcountries.eu/data/fin.svg\",\"id\":76,\"name\":\"Finland\"},\n" +
                "            {\"alpha2Code\":\"FR\",\"code\":\"+33\",\"icon\":\"https://restcountries.eu/data/fra.svg\",\"id\":77,\"name\":\"France\"},\n" +
                "            {\"alpha2Code\":\"GF\",\"code\":\"+594\",\"icon\":\"https://restcountries.eu/data/guf.svg\",\"id\":78,\"name\":\"French Guiana\"},\n" +
                "            {\"alpha2Code\":\"PF\",\"code\":\"+689\",\"icon\":\"https://restcountries.eu/data/pyf.svg\",\"id\":79,\"name\":\"French Polynesia\"},\n" +
                "            {\"alpha2Code\":\"TF\",\"code\":\"\",\"icon\":\"https://restcountries.eu/data/atf.svg\",\"id\":80,\"name\":\"French Southern Territories\"},\n" +
                "            {\"alpha2Code\":\"GA\",\"code\":\"+241\",\"icon\":\"https://restcountries.eu/data/gab.svg\",\"id\":81,\"name\":\"Gabon\"},\n" +
                "            {\"alpha2Code\":\"GM\",\"code\":\"+220\",\"icon\":\"https://restcountries.eu/data/gmb.svg\",\"id\":82,\"name\":\"Gambia\"},\n" +
                "            {\"alpha2Code\":\"GE\",\"code\":\"+995\",\"icon\":\"https://restcountries.eu/data/geo.svg\",\"id\":83,\"name\":\"Georgia\"},\n" +
                "            {\"alpha2Code\":\"DE\",\"code\":\"+49\",\"icon\":\"https://restcountries.eu/data/deu.svg\",\"id\":84,\"name\":\"Germany\"},\n" +
                "            {\"alpha2Code\":\"GH\",\"code\":\"+233\",\"icon\":\"https://restcountries.eu/data/gha.svg\",\"id\":85,\"name\":\"Ghana\"},\n" +
                "            {\"alpha2Code\":\"GI\",\"code\":\"+350\",\"icon\":\"https://restcountries.eu/data/gib.svg\",\"id\":86,\"name\":\"Gibraltar\"},\n" +
                "            {\"alpha2Code\":\"GR\",\"code\":\"+30\",\"icon\":\"https://restcountries.eu/data/grc.svg\",\"id\":87,\"name\":\"Greece\"},\n" +
                "            {\"alpha2Code\":\"GL\",\"code\":\"+299\",\"icon\":\"https://restcountries.eu/data/grl.svg\",\"id\":88,\"name\":\"Greenland\"},\n" +
                "            {\"alpha2Code\":\"GD\",\"code\":\"+1473\",\"icon\":\"https://restcountries.eu/data/grd.svg\",\"id\":89,\"name\":\"Grenada\"},\n" +
                "            {\"alpha2Code\":\"GP\",\"code\":\"+590\",\"icon\":\"https://restcountries.eu/data/glp.svg\",\"id\":90,\"name\":\"Guadeloupe\"},\n" +
                "            {\"alpha2Code\":\"GU\",\"code\":\"+1671\",\"icon\":\"https://restcountries.eu/data/gum.svg\",\"id\":91,\"name\":\"Guam\"},\n" +
                "            {\"alpha2Code\":\"GT\",\"code\":\"+502\",\"icon\":\"https://restcountries.eu/data/gtm.svg\",\"id\":92,\"name\":\"Guatemala\"},\n" +
                "            {\"alpha2Code\":\"GG\",\"code\":\"+44\",\"icon\":\"https://restcountries.eu/data/ggy.svg\",\"id\":93,\"name\":\"Guernsey\"},\n" +
                "            {\"alpha2Code\":\"GN\",\"code\":\"+224\",\"icon\":\"https://restcountries.eu/data/gin.svg\",\"id\":94,\"name\":\"Guinea\"},\n" +
                "            {\"alpha2Code\":\"GW\",\"code\":\"+245\",\"icon\":\"https://restcountries.eu/data/gnb.svg\",\"id\":95,\"name\":\"Guinea-Bissau\"},\n" +
                "            {\"alpha2Code\":\"GY\",\"code\":\"+592\",\"icon\":\"https://restcountries.eu/data/guy.svg\",\"id\":96,\"name\":\"Guyana\"},\n" +
                "            {\"alpha2Code\":\"HT\",\"code\":\"+509\",\"icon\":\"https://restcountries.eu/data/hti.svg\",\"id\":97,\"name\":\"Haiti\"},\n" +
                "            {\"alpha2Code\":\"HM\",\"code\":\"\",\"icon\":\"https://restcountries.eu/data/hmd.svg\",\"id\":98,\"name\":\"Heard Island and McDonald Islands\"},\n" +
                "            {\"alpha2Code\":\"VA\",\"code\":\"+379\",\"icon\":\"https://restcountries.eu/data/vat.svg\",\"id\":99,\"name\":\"Holy See\"},\n" +
                "            {\"alpha2Code\":\"HN\",\"code\":\"+504\",\"icon\":\"https://restcountries.eu/data/hnd.svg\",\"id\":100,\"name\":\"Honduras\"},\n" +
                "            {\"alpha2Code\":\"HK\",\"code\":\"+852\",\"icon\":\"https://restcountries.eu/data/hkg.svg\",\"id\":101,\"name\":\"Hong Kong\"},\n" +
                "            {\"alpha2Code\":\"HU\",\"code\":\"+36\",\"icon\":\"https://restcountries.eu/data/hun.svg\",\"id\":102,\"name\":\"Hungary\"},\n" +
                "            {\"alpha2Code\":\"IS\",\"code\":\"+354\",\"icon\":\"https://restcountries.eu/data/isl.svg\",\"id\":103,\"name\":\"Iceland\"},\n" +
                "            {\"alpha2Code\":\"IN\",\"code\":\"+91\",\"icon\":\"https://restcountries.eu/data/ind.svg\",\"id\":104,\"name\":\"India\"},\n" +
                "            {\"alpha2Code\":\"ID\",\"code\":\"+62\",\"icon\":\"https://restcountries.eu/data/idn.svg\",\"id\":105,\"name\":\"Indonesia\"},\n" +
                "            {\"alpha2Code\":\"IR\",\"code\":\"+98\",\"icon\":\"https://restcountries.eu/data/irn.svg\",\"id\":107,\"name\":\"Iran (Islamic Republic of)\"},\n" +
                "            {\"alpha2Code\":\"IQ\",\"code\":\"+964\",\"icon\":\"https://restcountries.eu/data/irq.svg\",\"id\":108,\"name\":\"Iraq\"},\n" +
                "            {\"alpha2Code\":\"IE\",\"code\":\"+353\",\"icon\":\"https://restcountries.eu/data/irl.svg\",\"id\":109,\"name\":\"Ireland\"},\n" +
                "            {\"alpha2Code\":\"IM\",\"code\":\"+44\",\"icon\":\"https://restcountries.eu/data/imn.svg\",\"id\":110,\"name\":\"Isle of Man\"},\n" +
                "            {\"alpha2Code\":\"IL\",\"code\":\"+972\",\"icon\":\"https://restcountries.eu/data/isr.svg\",\"id\":111,\"name\":\"Israel\"},\n" +
                "            {\"alpha2Code\":\"IT\",\"code\":\"+39\",\"icon\":\"https://restcountries.eu/data/ita.svg\",\"id\":112,\"name\":\"Italy\"},\n" +
                "            {\"alpha2Code\":\"JM\",\"code\":\"+1876\",\"icon\":\"https://restcountries.eu/data/jam.svg\",\"id\":113,\"name\":\"Jamaica\"},\n" +
                "            {\"alpha2Code\":\"JP\",\"code\":\"+81\",\"icon\":\"https://restcountries.eu/data/jpn.svg\",\"id\":114,\"name\":\"Japan\"},\n" +
                "            {\"alpha2Code\":\"JE\",\"code\":\"+44\",\"icon\":\"https://restcountries.eu/data/jey.svg\",\"id\":115,\"name\":\"Jersey\"},\n" +
                "            {\"alpha2Code\":\"JO\",\"code\":\"+962\",\"icon\":\"https://restcountries.eu/data/jor.svg\",\"id\":116,\"name\":\"Jordan\"},\n" +
                "            {\"alpha2Code\":\"KZ\",\"code\":\"+76\",\"icon\":\"https://restcountries.eu/data/kaz.svg\",\"id\":117,\"name\":\"Kazakhstan\"},\n" +
                "            {\"alpha2Code\":\"KE\",\"code\":\"+254\",\"icon\":\"https://restcountries.eu/data/ken.svg\",\"id\":118,\"name\":\"Kenya\"},\n" +
                "            {\"alpha2Code\":\"KI\",\"code\":\"+686\",\"icon\":\"https://restcountries.eu/data/kir.svg\",\"id\":119,\"name\":\"Kiribati\"},\n" +
                "            {\"alpha2Code\":\"KP\",\"code\":\"+850\",\"icon\":\"https://restcountries.eu/data/prk.svg\",\"id\":165,\"name\":\"Korea (Democratic People\\\\u0027s Republic of)\"},\n" +
                "            {\"alpha2Code\":\"KR\",\"code\":\"+82\",\"icon\":\"https://restcountries.eu/data/kor.svg\",\"id\":210,\"name\":\"Korea (Republic of)\"},\n" +
                "            {\"alpha2Code\":\"KW\",\"code\":\"+965\",\"icon\":\"https://restcountries.eu/data/kwt.svg\",\"id\":120,\"name\":\"Kuwait\"},\n" +
                "            {\"alpha2Code\":\"KG\",\"code\":\"+996\",\"icon\":\"https://restcountries.eu/data/kgz.svg\",\"id\":121,\"name\":\"Kyrgyzstan\"},\n" +
                "            {\"alpha2Code\":\"LA\",\"code\":\"+856\",\"icon\":\"https://restcountries.eu/data/lao.svg\",\"id\":122,\"name\":\"Lao People\\\\u0027s Democratic Republic\"},\n" +
                "            {\"alpha2Code\":\"LV\",\"code\":\"+371\",\"icon\":\"https://restcountries.eu/data/lva.svg\",\"id\":123,\"name\":\"Latvia\"},\n" +
                "            {\"alpha2Code\":\"LB\",\"code\":\"+961\",\"icon\":\"https://restcountries.eu/data/lbn.svg\",\"id\":124,\"name\":\"Lebanon\"},\n" +
                "            {\"alpha2Code\":\"LS\",\"code\":\"+266\",\"icon\":\"https://restcountries.eu/data/lso.svg\",\"id\":125,\"name\":\"Lesotho\"},\n" +
                "            {\"alpha2Code\":\"LR\",\"code\":\"+231\",\"icon\":\"https://restcountries.eu/data/lbr.svg\",\"id\":126,\"name\":\"Liberia\"},\n" +
                "            {\"alpha2Code\":\"LY\",\"code\":\"+218\",\"icon\":\"https://restcountries.eu/data/lby.svg\",\"id\":127,\"name\":\"Libya\"},\n" +
                "            {\"alpha2Code\":\"LI\",\"code\":\"+423\",\"icon\":\"https://restcountries.eu/data/lie.svg\",\"id\":128,\"name\":\"Liechtenstein\"},\n" +
                "            {\"alpha2Code\":\"LT\",\"code\":\"+370\",\"icon\":\"https://restcountries.eu/data/ltu.svg\",\"id\":129,\"name\":\"Lithuania\"},\n" +
                "            {\"alpha2Code\":\"LU\",\"code\":\"+352\",\"icon\":\"https://restcountries.eu/data/lux.svg\",\"id\":130,\"name\":\"Luxembourg\"},\n" +
                "            {\"alpha2Code\":\"MO\",\"code\":\"+853\",\"icon\":\"https://restcountries.eu/data/mac.svg\",\"id\":131,\"name\":\"Macao\"},\n" +
                "            {\"alpha2Code\":\"MK\",\"code\":\"+389\",\"icon\":\"https://restcountries.eu/data/mkd.svg\",\"id\":132,\"name\":\"Macedonia (the former Yugoslav Republic of)\"},\n" +
                "            {\"alpha2Code\":\"MG\",\"code\":\"+261\",\"icon\":\"https://restcountries.eu/data/mdg.svg\",\"id\":133,\"name\":\"Madagascar\"},\n" +
                "            {\"alpha2Code\":\"MW\",\"code\":\"+265\",\"icon\":\"https://restcountries.eu/data/mwi.svg\",\"id\":134,\"name\":\"Malawi\"},\n" +
                "            {\"alpha2Code\":\"MY\",\"code\":\"+60\",\"icon\":\"https://restcountries.eu/data/mys.svg\",\"id\":135,\"name\":\"Malaysia\"},\n" +
                "            {\"alpha2Code\":\"MV\",\"code\":\"+960\",\"icon\":\"https://restcountries.eu/data/mdv.svg\",\"id\":136,\"name\":\"Maldives\"},\n" +
                "            {\"alpha2Code\":\"ML\",\"code\":\"+223\",\"icon\":\"https://restcountries.eu/data/mli.svg\",\"id\":137,\"name\":\"Mali\"},\n" +
                "            {\"alpha2Code\":\"MT\",\"code\":\"+356\",\"icon\":\"https://restcountries.eu/data/mlt.svg\",\"id\":138,\"name\":\"Malta\"},\n" +
                "            {\"alpha2Code\":\"MH\",\"code\":\"+692\",\"icon\":\"https://restcountries.eu/data/mhl.svg\",\"id\":139,\"name\":\"Marshall Islands\"},\n" +
                "            {\"alpha2Code\":\"MQ\",\"code\":\"+596\",\"icon\":\"https://restcountries.eu/data/mtq.svg\",\"id\":140,\"name\":\"Martinique\"},\n" +
                "            {\"alpha2Code\":\"MR\",\"code\":\"+222\",\"icon\":\"https://restcountries.eu/data/mrt.svg\",\"id\":141,\"name\":\"Mauritania\"},\n" +
                "            {\"alpha2Code\":\"MU\",\"code\":\"+230\",\"icon\":\"https://restcountries.eu/data/mus.svg\",\"id\":142,\"name\":\"Mauritius\"},\n" +
                "            {\"alpha2Code\":\"YT\",\"code\":\"+262\",\"icon\":\"https://restcountries.eu/data/myt.svg\",\"id\":143,\"name\":\"Mayotte\"},\n" +
                "            {\"alpha2Code\":\"MX\",\"code\":\"+52\",\"icon\":\"https://restcountries.eu/data/mex.svg\",\"id\":144,\"name\":\"Mexico\"},\n" +
                "            {\"alpha2Code\":\"FM\",\"code\":\"+691\",\"icon\":\"https://restcountries.eu/data/fsm.svg\",\"id\":145,\"name\":\"Micronesia (Federated States of)\"},\n" +
                "            {\"alpha2Code\":\"MD\",\"code\":\"+373\",\"icon\":\"https://restcountries.eu/data/mda.svg\",\"id\":146,\"name\":\"Moldova (Republic of)\"},\n" +
                "            {\"alpha2Code\":\"MC\",\"code\":\"+377\",\"icon\":\"https://restcountries.eu/data/mco.svg\",\"id\":147,\"name\":\"Monaco\"},\n" +
                "            {\"alpha2Code\":\"MN\",\"code\":\"+976\",\"icon\":\"https://restcountries.eu/data/mng.svg\",\"id\":148,\"name\":\"Mongolia\"},\n" +
                "            {\"alpha2Code\":\"ME\",\"code\":\"+382\",\"icon\":\"https://restcountries.eu/data/mne.svg\",\"id\":149,\"name\":\"Montenegro\"},\n" +
                "            {\"alpha2Code\":\"MS\",\"code\":\"+1664\",\"icon\":\"https://restcountries.eu/data/msr.svg\",\"id\":150,\"name\":\"Montserrat\"},\n" +
                "            {\"alpha2Code\":\"MA\",\"code\":\"+212\",\"icon\":\"https://restcountries.eu/data/mar.svg\",\"id\":151,\"name\":\"Morocco\"},\n" +
                "            {\"alpha2Code\":\"MZ\",\"code\":\"+258\",\"icon\":\"https://restcountries.eu/data/moz.svg\",\"id\":152,\"name\":\"Mozambique\"},\n" +
                "            {\"alpha2Code\":\"MM\",\"code\":\"+95\",\"icon\":\"https://restcountries.eu/data/mmr.svg\",\"id\":153,\"name\":\"Myanmar\"},\n" +
                "            {\"alpha2Code\":\"NA\",\"code\":\"+264\",\"icon\":\"https://restcountries.eu/data/nam.svg\",\"id\":154,\"name\":\"Namibia\"},\n" +
                "            {\"alpha2Code\":\"NR\",\"code\":\"+674\",\"icon\":\"https://restcountries.eu/data/nru.svg\",\"id\":155,\"name\":\"Nauru\"},\n" +
                "            {\"alpha2Code\":\"NP\",\"code\":\"+977\",\"icon\":\"https://restcountries.eu/data/npl.svg\",\"id\":156,\"name\":\"Nepal\"},\n" +
                "            {\"alpha2Code\":\"NL\",\"code\":\"+31\",\"icon\":\"https://restcountries.eu/data/nld.svg\",\"id\":157,\"name\":\"Netherlands\"},\n" +
                "            {\"alpha2Code\":\"NC\",\"code\":\"+687\",\"icon\":\"https://restcountries.eu/data/ncl.svg\",\"id\":158,\"name\":\"New Caledonia\"},\n" +
                "            {\"alpha2Code\":\"NZ\",\"code\":\"+64\",\"icon\":\"https://restcountries.eu/data/nzl.svg\",\"id\":159,\"name\":\"New Zealand\"},\n" +
                "            {\"alpha2Code\":\"NI\",\"code\":\"+505\",\"icon\":\"https://restcountries.eu/data/nic.svg\",\"id\":160,\"name\":\"Nicaragua\"},\n" +
                "            {\"alpha2Code\":\"NE\",\"code\":\"+227\",\"icon\":\"https://restcountries.eu/data/ner.svg\",\"id\":161,\"name\":\"Niger\"},\n" +
                "            {\"alpha2Code\":\"NG\",\"code\":\"+234\",\"icon\":\"https://restcountries.eu/data/nga.svg\",\"id\":162,\"name\":\"Nigeria\"},\n" +
                "            {\"alpha2Code\":\"NU\",\"code\":\"+683\",\"icon\":\"https://restcountries.eu/data/niu.svg\",\"id\":163,\"name\":\"Niue\"},\n" +
                "            {\"alpha2Code\":\"NF\",\"code\":\"+672\",\"icon\":\"https://restcountries.eu/data/nfk.svg\",\"id\":164,\"name\":\"Norfolk Island\"},\n" +
                "            {\"alpha2Code\":\"MP\",\"code\":\"+1670\",\"icon\":\"https://restcountries.eu/data/mnp.svg\",\"id\":166,\"name\":\"Northern Mariana Islands\"},\n" +
                "            {\"alpha2Code\":\"NO\",\"code\":\"+47\",\"icon\":\"https://restcountries.eu/data/nor.svg\",\"id\":167,\"name\":\"Norway\"},\n" +
                "            {\"alpha2Code\":\"OM\",\"code\":\"+968\",\"icon\":\"https://restcountries.eu/data/omn.svg\",\"id\":168,\"name\":\"Oman\"},\n" +
                "            {\"alpha2Code\":\"PK\",\"code\":\"+92\",\"icon\":\"https://restcountries.eu/data/pak.svg\",\"id\":169,\"name\":\"Pakistan\"},\n" +
                "            {\"alpha2Code\":\"PW\",\"code\":\"+680\",\"icon\":\"https://restcountries.eu/data/plw.svg\",\"id\":170,\"name\":\"Palau\"},\n" +
                "            {\"alpha2Code\":\"PS\",\"code\":\"+970\",\"icon\":\"https://restcountries.eu/data/pse.svg\",\"id\":171,\"name\":\"Palestine, State of\"},\n" +
                "            {\"alpha2Code\":\"PA\",\"code\":\"+507\",\"icon\":\"https://restcountries.eu/data/pan.svg\",\"id\":172,\"name\":\"Panama\"},\n" +
                "            {\"alpha2Code\":\"PG\",\"code\":\"+675\",\"icon\":\"https://restcountries.eu/data/png.svg\",\"id\":173,\"name\":\"Papua New Guinea\"},\n" +
                "            {\"alpha2Code\":\"PY\",\"code\":\"+595\",\"icon\":\"https://restcountries.eu/data/pry.svg\",\"id\":174,\"name\":\"Paraguay\"},\n" +
                "            {\"alpha2Code\":\"PE\",\"code\":\"+51\",\"icon\":\"https://restcountries.eu/data/per.svg\",\"id\":175,\"name\":\"Peru\"},\n" +
                "            {\"alpha2Code\":\"PH\",\"code\":\"+63\",\"icon\":\"https://restcountries.eu/data/phl.svg\",\"id\":176,\"name\":\"Philippines\"},\n" +
                "            {\"alpha2Code\":\"PN\",\"code\":\"+64\",\"icon\":\"https://restcountries.eu/data/pcn.svg\",\"id\":177,\"name\":\"Pitcairn\"},\n" +
                "            {\"alpha2Code\":\"PL\",\"code\":\"+48\",\"icon\":\"https://restcountries.eu/data/pol.svg\",\"id\":178,\"name\":\"Poland\"},\n" +
                "            {\"alpha2Code\":\"PT\",\"code\":\"+351\",\"icon\":\"https://restcountries.eu/data/prt.svg\",\"id\":179,\"name\":\"Portugal\"},\n" +
                "            {\"alpha2Code\":\"PR\",\"code\":\"+1787\",\"icon\":\"https://restcountries.eu/data/pri.svg\",\"id\":180,\"name\":\"Puerto Rico\"},\n" +
                "            {\"alpha2Code\":\"QA\",\"code\":\"+974\",\"icon\":\"https://restcountries.eu/data/qat.svg\",\"id\":181,\"name\":\"Qatar\"},\n" +
                "            {\"alpha2Code\":\"XK\",\"code\":\"+383\",\"icon\":\"https://restcountries.eu/data/kos.svg\",\"id\":182,\"name\":\"Republic of Kosovo\"},\n" +
                "            {\"alpha2Code\":\"RO\",\"code\":\"+40\",\"icon\":\"https://restcountries.eu/data/rou.svg\",\"id\":184,\"name\":\"Romania\"},\n" +
                "            {\"alpha2Code\":\"RU\",\"code\":\"+7\",\"icon\":\"https://restcountries.eu/data/rus.svg\",\"id\":185,\"name\":\"Russian Federation\"},\n" +
                "            {\"alpha2Code\":\"RW\",\"code\":\"+250\",\"icon\":\"https://restcountries.eu/data/rwa.svg\",\"id\":186,\"name\":\"Rwanda\"},\n" +
                "            {\"alpha2Code\":\"RE\",\"code\":\"+262\",\"icon\":\"https://restcountries.eu/data/reu.svg\",\"id\":183,\"name\":\"Réunion\"},\n" +
                "            {\"alpha2Code\":\"BL\",\"code\":\"+590\",\"icon\":\"https://restcountries.eu/data/blm.svg\",\"id\":187,\"name\":\"Saint Barthélemy\"},\n" +
                "            {\"alpha2Code\":\"SH\",\"code\":\"+290\",\"icon\":\"https://restcountries.eu/data/shn.svg\",\"id\":188,\"name\":\"Saint Helena, Ascension and Tristan da Cunha\"},\n" +
                "            {\"alpha2Code\":\"KN\",\"code\":\"+1869\",\"icon\":\"https://restcountries.eu/data/kna.svg\",\"id\":189,\"name\":\"Saint Kitts and Nevis\"},\n" +
                "            {\"alpha2Code\":\"LC\",\"code\":\"+1758\",\"icon\":\"https://restcountries.eu/data/lca.svg\",\"id\":190,\"name\":\"Saint Lucia\"},\n" +
                "            {\"alpha2Code\":\"MF\",\"code\":\"+590\",\"icon\":\"https://restcountries.eu/data/maf.svg\",\"id\":191,\"name\":\"Saint Martin (French part)\"},\n" +
                "            {\"alpha2Code\":\"PM\",\"code\":\"+508\",\"icon\":\"https://restcountries.eu/data/spm.svg\",\"id\":192,\"name\":\"Saint Pierre and Miquelon\"},\n" +
                "            {\"alpha2Code\":\"VC\",\"code\":\"+1784\",\"icon\":\"https://restcountries.eu/data/vct.svg\",\"id\":193,\"name\":\"Saint Vincent and the Grenadines\"},\n" +
                "            {\"alpha2Code\":\"WS\",\"code\":\"+685\",\"icon\":\"https://restcountries.eu/data/wsm.svg\",\"id\":194,\"name\":\"Samoa\"},\n" +
                "            {\"alpha2Code\":\"SM\",\"code\":\"+378\",\"icon\":\"https://restcountries.eu/data/smr.svg\",\"id\":195,\"name\":\"San Marino\"},\n" +
                "            {\"alpha2Code\":\"ST\",\"code\":\"+239\",\"icon\":\"https://restcountries.eu/data/stp.svg\",\"id\":196,\"name\":\"Sao Tome and Principe\"},\n" +
                "            {\"alpha2Code\":\"SA\",\"code\":\"+966\",\"icon\":\"https://restcountries.eu/data/sau.svg\",\"id\":197,\"name\":\"Saudi Arabia\"},\n" +
                "            {\"alpha2Code\":\"SN\",\"code\":\"+221\",\"icon\":\"https://restcountries.eu/data/sen.svg\",\"id\":198,\"name\":\"Senegal\"},\n" +
                "            {\"alpha2Code\":\"RS\",\"code\":\"+381\",\"icon\":\"https://restcountries.eu/data/srb.svg\",\"id\":199,\"name\":\"Serbia\"},\n" +
                "            {\"alpha2Code\":\"SC\",\"code\":\"+248\",\"icon\":\"https://restcountries.eu/data/syc.svg\",\"id\":200,\"name\":\"Seychelles\"},\n" +
                "            {\"alpha2Code\":\"SL\",\"code\":\"+232\",\"icon\":\"https://restcountries.eu/data/sle.svg\",\"id\":201,\"name\":\"Sierra Leone\"},\n" +
                "            {\"alpha2Code\":\"SG\",\"code\":\"+65\",\"icon\":\"https://restcountries.eu/data/sgp.svg\",\"id\":202,\"name\":\"Singapore\"},\n" +
                "            {\"alpha2Code\":\"SX\",\"code\":\"+1721\",\"icon\":\"https://restcountries.eu/data/sxm.svg\",\"id\":203,\"name\":\"Sint Maarten (Dutch part)\"},\n" +
                "            {\"alpha2Code\":\"SK\",\"code\":\"+421\",\"icon\":\"https://restcountries.eu/data/svk.svg\",\"id\":204,\"name\":\"Slovakia\"},\n" +
                "            {\"alpha2Code\":\"SI\",\"code\":\"+386\",\"icon\":\"https://restcountries.eu/data/svn.svg\",\"id\":205,\"name\":\"Slovenia\"},\n" +
                "            {\"alpha2Code\":\"SB\",\"code\":\"+677\",\"icon\":\"https://restcountries.eu/data/slb.svg\",\"id\":206,\"name\":\"Solomon Islands\"},\n" +
                "            {\"alpha2Code\":\"SO\",\"code\":\"+252\",\"icon\":\"https://restcountries.eu/data/som.svg\",\"id\":207,\"name\":\"Somalia\"},\n" +
                "            {\"alpha2Code\":\"ZA\",\"code\":\"+27\",\"icon\":\"https://restcountries.eu/data/zaf.svg\",\"id\":208,\"name\":\"South Africa\"},\n" +
                "            {\"alpha2Code\":\"GS\",\"code\":\"+500\",\"icon\":\"https://restcountries.eu/data/sgs.svg\",\"id\":209,\"name\":\"South Georgia and the South Sandwich Islands\"},\n" +
                "            {\"alpha2Code\":\"SS\",\"code\":\"+211\",\"icon\":\"https://restcountries.eu/data/ssd.svg\",\"id\":211,\"name\":\"South Sudan\"},\n" +
                "            {\"alpha2Code\":\"ES\",\"code\":\"+34\",\"icon\":\"https://restcountries.eu/data/esp.svg\",\"id\":212,\"name\":\"Spain\"},\n" +
                "            {\"alpha2Code\":\"LK\",\"code\":\"+94\",\"icon\":\"https://restcountries.eu/data/lka.svg\",\"id\":213,\"name\":\"Sri Lanka\"},\n" +
                "            {\"alpha2Code\":\"SD\",\"code\":\"+249\",\"icon\":\"https://restcountries.eu/data/sdn.svg\",\"id\":214,\"name\":\"Sudan\"},\n" +
                "            {\"alpha2Code\":\"SR\",\"code\":\"+597\",\"icon\":\"https://restcountries.eu/data/sur.svg\",\"id\":215,\"name\":\"Suriname\"},\n" +
                "            {\"alpha2Code\":\"SJ\",\"code\":\"+4779\",\"icon\":\"https://restcountries.eu/data/sjm.svg\",\"id\":216,\"name\":\"Svalbard and Jan Mayen\"},\n" +
                "            {\"alpha2Code\":\"SZ\",\"code\":\"+268\",\"icon\":\"https://restcountries.eu/data/swz.svg\",\"id\":217,\"name\":\"Swaziland\"},\n" +
                "            {\"alpha2Code\":\"SE\",\"code\":\"+46\",\"icon\":\"https://restcountries.eu/data/swe.svg\",\"id\":218,\"name\":\"Sweden\"},\n" +
                "            {\"alpha2Code\":\"CH\",\"code\":\"+41\",\"icon\":\"https://restcountries.eu/data/che.svg\",\"id\":219,\"name\":\"Switzerland\"},\n" +
                "            {\"alpha2Code\":\"SY\",\"code\":\"+963\",\"icon\":\"https://restcountries.eu/data/syr.svg\",\"id\":220,\"name\":\"Syrian Arab Republic\"},\n" +
                "            {\"alpha2Code\":\"TW\",\"code\":\"+886\",\"icon\":\"https://restcountries.eu/data/twn.svg\",\"id\":221,\"name\":\"Taiwan\"},\n" +
                "            {\"alpha2Code\":\"TJ\",\"code\":\"+992\",\"icon\":\"https://restcountries.eu/data/tjk.svg\",\"id\":222,\"name\":\"Tajikistan\"},\n" +
                "            {\"alpha2Code\":\"TZ\",\"code\":\"+255\",\"icon\":\"https://restcountries.eu/data/tza.svg\",\"id\":223,\"name\":\"Tanzania, United Republic of\"},\n" +
                "            {\"alpha2Code\":\"TH\",\"code\":\"+66\",\"icon\":\"https://restcountries.eu/data/tha.svg\",\"id\":224,\"name\":\"Thailand\"},\n" +
                "            {\"alpha2Code\":\"TL\",\"code\":\"+670\",\"icon\":\"https://restcountries.eu/data/tls.svg\",\"id\":225,\"name\":\"Timor-Leste\"},\n" +
                "            {\"alpha2Code\":\"TG\",\"code\":\"+228\",\"icon\":\"https://restcountries.eu/data/tgo.svg\",\"id\":226,\"name\":\"Togo\"},\n" +
                "            {\"alpha2Code\":\"TK\",\"code\":\"+690\",\"icon\":\"https://restcountries.eu/data/tkl.svg\",\"id\":227,\"name\":\"Tokelau\"},\n" +
                "            {\"alpha2Code\":\"TO\",\"code\":\"+676\",\"icon\":\"https://restcountries.eu/data/ton.svg\",\"id\":228,\"name\":\"Tonga\"},\n" +
                "            {\"alpha2Code\":\"TT\",\"code\":\"+1868\",\"icon\":\"https://restcountries.eu/data/tto.svg\",\"id\":229,\"name\":\"Trinidad and Tobago\"},\n" +
                "            {\"alpha2Code\":\"TN\",\"code\":\"+216\",\"icon\":\"https://restcountries.eu/data/tun.svg\",\"id\":230,\"name\":\"Tunisia\"},\n" +
                "            {\"alpha2Code\":\"TR\",\"code\":\"+90\",\"icon\":\"https://restcountries.eu/data/tur.svg\",\"id\":231,\"name\":\"Turkey\"},\n" +
                "            {\"alpha2Code\":\"TM\",\"code\":\"+993\",\"icon\":\"https://restcountries.eu/data/tkm.svg\",\"id\":232,\"name\":\"Turkmenistan\"},\n" +
                "            {\"alpha2Code\":\"TC\",\"code\":\"+1649\",\"icon\":\"https://restcountries.eu/data/tca.svg\",\"id\":233,\"name\":\"Turks and Caicos Islands\"},\n" +
                "            {\"alpha2Code\":\"TV\",\"code\":\"+688\",\"icon\":\"https://restcountries.eu/data/tuv.svg\",\"id\":234,\"name\":\"Tuvalu\"},\n" +
                "            {\"alpha2Code\":\"UG\",\"code\":\"+256\",\"icon\":\"https://restcountries.eu/data/uga.svg\",\"id\":235,\"name\":\"Uganda\"},\n" +
                "            {\"alpha2Code\":\"UA\",\"code\":\"+380\",\"icon\":\"https://restcountries.eu/data/ukr.svg\",\"id\":236,\"name\":\"Ukraine\"},\n" +
                "            {\"alpha2Code\":\"AE\",\"code\":\"+971\",\"icon\":\"https://restcountries.eu/data/are.svg\",\"id\":237,\"name\":\"United Arab Emirates\"},\n" +
                "            {\"alpha2Code\":\"GB\",\"code\":\"+44\",\"icon\":\"https://restcountries.eu/data/gbr.svg\",\"id\":238,\"name\":\"United Kingdom of Great Britain and Northern Ireland\"},\n" +
                "            {\"alpha2Code\":\"UM\",\"code\":\"\",\"icon\":\"https://restcountries.eu/data/umi.svg\",\"id\":33,\"name\":\"United States Minor Outlying Islands\"},\n" +
                "            {\"alpha2Code\":\"US\",\"code\":\"+1\",\"icon\":\"https://restcountries.eu/data/usa.svg\",\"id\":239,\"name\":\"United States of America\"},\n" +
                "            {\"alpha2Code\":\"UY\",\"code\":\"+598\",\"icon\":\"https://restcountries.eu/data/ury.svg\",\"id\":240,\"name\":\"Uruguay\"},\n" +
                "            {\"alpha2Code\":\"UZ\",\"code\":\"+998\",\"icon\":\"https://restcountries.eu/data/uzb.svg\",\"id\":241,\"name\":\"Uzbekistan\"},\n" +
                "            {\"alpha2Code\":\"VU\",\"code\":\"+678\",\"icon\":\"https://restcountries.eu/data/vut.svg\",\"id\":242,\"name\":\"Vanuatu\"},\n" +
                "            {\"alpha2Code\":\"VE\",\"code\":\"+58\",\"icon\":\"https://restcountries.eu/data/ven.svg\",\"id\":243,\"name\":\"Venezuela (Bolivarian Republic of)\"},\n" +
                "            {\"alpha2Code\":\"VN\",\"code\":\"+84\",\"icon\":\"https://restcountries.eu/data/vnm.svg\",\"id\":244,\"name\":\"Viet Nam\"},\n" +
                "            {\"alpha2Code\":\"VG\",\"code\":\"+1284\",\"icon\":\"https://restcountries.eu/data/vgb.svg\",\"id\":34,\"name\":\"Virgin Islands (British)\"},\n" +
                "            {\"alpha2Code\":\"VI\",\"code\":\"+1 340\",\"icon\":\"https://restcountries.eu/data/vir.svg\",\"id\":35,\"name\":\"Virgin Islands (U.S.)\"},\n" +
                "            {\"alpha2Code\":\"WF\",\"code\":\"+681\",\"icon\":\"https://restcountries.eu/data/wlf.svg\",\"id\":245,\"name\":\"Wallis and Futuna\"},\n" +
                "            {\"alpha2Code\":\"EH\",\"code\":\"+212\",\"icon\":\"https://restcountries.eu/data/esh.svg\",\"id\":246,\"name\":\"Western Sahara\"},\n" +
                "            {\"alpha2Code\":\"YE\",\"code\":\"+967\",\"icon\":\"https://restcountries.eu/data/yem.svg\",\"id\":247,\"name\":\"Yemen\"},\n" +
                "            {\"alpha2Code\":\"ZM\",\"code\":\"+260\",\"icon\":\"https://restcountries.eu/data/zmb.svg\",\"id\":248,\"name\":\"Zambia\"},\n" +
                "            {\"alpha2Code\":\"ZW\",\"code\":\"+263\",\"icon\":\"https://restcountries.eu/data/zwe.svg\",\"id\":249,\"name\":\"Zimbabwe\"},\n" +
                "            {\"alpha2Code\":\"AX\",\"code\":\"+358\",\"icon\":\"https://restcountries.eu/data/ala.svg\",\"id\":1,\"name\":\"Åland Islands\"}]";

        try {
            JSONArray array = new JSONArray(data);
            for (int i=0; i<array.length(); i++){
                JSONObject item = array.getJSONObject(i);
                CountriesModel cm = new CountriesModel();
                cm.setId(item.getInt("id"));
                cm.setAlpha2Code(item.getString("alpha2Code"));
                cm.setCode(item.getString("code"));
                cm.setIcon(item.getString("icon"));
                cm.setName(item.getString("name"));
                list.add(cm);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}