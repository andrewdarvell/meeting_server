package ru.darvell.meetingserver.workers;

import ru.darvell.meetingserver.utils.Response;

import java.util.Map;

/**
 * Created by darvell on 10.01.15.
 */
public interface Worker {
    public Response doAction(Map<String, String> params);
}
