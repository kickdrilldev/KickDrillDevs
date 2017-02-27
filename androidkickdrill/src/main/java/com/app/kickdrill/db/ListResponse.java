package com.app.kickdrill.db;

import com.app.kickdrill.db.MasterPojo;

import java.util.List;

/**
 * Created by vyankatesh.jadhav on 2/7/2017.
 */

public interface ListResponse<R extends MasterPojo> extends MasterPojo, List<R> {
}

