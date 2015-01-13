package ru.darvell.meetingserver.workers;

import ru.darvell.meetingserver.utils.Response;
import ru.darvell.meetingserver.utils.ResponseParams;

import java.util.Map;

/**
 * Created by darvell on 10.01.15.
 */
public interface Worker {
    public Response doAction(Map<String, String> params);
}
