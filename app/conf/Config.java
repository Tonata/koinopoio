package conf;

import com.typesafe.config.ConfigFactory;

/**
 * Created by martian on 2017/03/09.
 */
public interface Config {

    com.typesafe.config.Config config  = ConfigFactory.load();
}
