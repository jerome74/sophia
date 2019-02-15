package indra.com.icertify.activities.service.robot;

import android.content.Intent;



public enum ServiceType 
{
    INCOMING(10001),
    REGULAR(10002),
    UNKNOWN(10003),
    IMMEDIATE(10004);

    public final int resId;

    ServiceType(int resId) {
        this.resId = resId;
    }

    public static final String EXTRA = "it.mygeo.project.service.robot.ServiceType";

    public static ServiceType fromIntent(Intent intent) {
        if (intent.hasExtra(EXTRA)) {
            final String name = intent.getStringExtra(EXTRA);
            for (ServiceType type : values()) {
                if (type.name().equals(name)) {
                    return type;
                }
            }
            return UNKNOWN;
        } else {
            return UNKNOWN;
        }
    }
}
