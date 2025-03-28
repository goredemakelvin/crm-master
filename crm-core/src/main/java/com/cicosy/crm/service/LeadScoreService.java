package com.cicosy.crm.service;

import com.cicosy.crm.data.LeadData;
import com.cicosy.crm.entity.Lead;
import com.cicosy.crm.entity.LeadScore;

public  abstract  class LeadScoreService  extends  AbstractService<LeadScore>{

    public abstract void assignScore(LeadData leadData, Lead lead);
}
