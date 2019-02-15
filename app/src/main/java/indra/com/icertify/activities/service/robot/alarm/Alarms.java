/* Copyright (c) 2009 Christoph Studer <chstuder@gmail.com>
 * Copyright (c) 2010 Jan Berkel <jan.berkel@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package indra.com.icertify.activities.service.robot.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.UUID;

import indra.com.icertify.activities.constants.UTIL_SOPHIA;
import indra.com.icertify.activities.service.ServiceManager;
import indra.com.icertify.activities.service.robot.ServiceType;



public class Alarms<S extends ServiceManager> {

    /**
     * Default value
     */
    public static final boolean ENABLE_AUTO_SYNC = false;
    /**
     * Default value
     */
    public static final int INCOMING_TIMEOUT_SECONDS = 60 * 3;
    /**
     * Default value
     */
    public static final int REGULAR_TIMEOUT_SECONDS = 2 * 60 * 60; // 2h

    private static final int BOOT_BACKUP_DELAY = 60;

    private Context mContext;
    private Class<S> service;

    public Alarms(Context context , Class<S> s) {
        this.service = s;
    }

    Alarms(Context context) {
        mContext = context.getApplicationContext();

    }

    public long scheduleIncomingBackup() {
        return scheduleService(INCOMING_TIMEOUT_SECONDS, ServiceType.INCOMING, false);
    }

    public long scheduleRegularBackup() {
        return scheduleService(REGULAR_TIMEOUT_SECONDS, ServiceType.REGULAR, false);
    }

    public long scheduleImmediateBackup() {
        return scheduleService(-1, ServiceType.IMMEDIATE, true);
    }

    public void  cancel(ServiceType serviceType) {
        getAlarmManager(mContext).cancel(createPendingIntent(mContext, serviceType));
    }

    private long scheduleService(int inSeconds, ServiceType serviceType, boolean force)
    {    
        if (force ||  inSeconds > 0) {
            final long atTime = System.currentTimeMillis() + (inSeconds * 1000);
            getAlarmManager(mContext).set(AlarmManager.RTC_WAKEUP, atTime, createPendingIntent(mContext, serviceType));
            
            Log.v(UTIL_SOPHIA.SOPHIA, "Scheduled backup due " + (inSeconds > 0 ? "in " + inSeconds + " seconds" : "now"));
            
            return atTime;
        } 
        else 
        {
            Log.v(UTIL_SOPHIA.SOPHIA, "Not scheduling backup because auto sync is disabled.");
            return -1;
        }
    }

    private static AlarmManager getAlarmManager(Context ctx) {
        return (AlarmManager) ctx.getSystemService(Context.ALARM_SERVICE);
    }

    
    private PendingIntent createPendingIntent(Context ctx, ServiceType serviceType) {
        final UUID uuid = UUID.randomUUID();

        final Intent intent = (new Intent(ctx, service))
            .setAction(serviceType.name() + "-" + uuid.toString()) // create fresh pending intent
            .putExtra(serviceType.EXTRA, serviceType.name());

        return PendingIntent.getService(ctx, 0, intent, 0);
    }
}
