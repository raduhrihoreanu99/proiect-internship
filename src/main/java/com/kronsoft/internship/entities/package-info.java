@FilterDefs({
    @FilterDef(name = FilterConstants.NOT_EXPIRED_APPOINTMENTS, defaultCondition = "start_date > now()"),
    @FilterDef(name = FilterConstants.PATIENTS_OVER_EIGHTEEN, defaultCondition = "birthdate < '2004-01-01'")
})

package com.kronsoft.internship.entities;

import com.kronsoft.internship.constants.FilterConstants;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;

