package bg.softuni.milionrecepti.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import java.util.Map;

public class CloudinaryConfig {
    public Map params = ObjectUtils.asMap(
            "use_filename", true,
            "unique_filename", true,
            "overwrite", true
    );

    public Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "dvy0snmvn",
            "api_key", "462311458778739",
            "api_secret", "D9m8epc4F9_anWzdTVE4hcnZpVw"));
}
