package com.huateng.ebank.entity.dao.workflow;


import java.util.List;

import org.hibernate.type.Type;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.huateng.ebank.business.common.ErrorCode;
import com.huateng.ebank.entity.data.workflow.WorkflowRouteParam;
import com.huateng.ebank.framework.exceptions.CommonException;
import com.huateng.ebank.framework.util.ExceptionUtil;

/**
 * @author valley
 * @date 2005-06-01
 * @desc 数据库访问类
 */
public class WorkflowRouteParamDAO extends HibernateDaoSupport {

    public WorkflowRouteParamDAO() {
        super();
    }

    /**
     * 根据Hibernate ID查询记录，如果没有找到记录，则抛出异常
     *
     * @param id
     * @return LimitParam
     * @throws CommonException
     */
    public WorkflowRouteParam query(long id) throws CommonException {
        try {
            return (WorkflowRouteParam) this.getHibernateTemplate().load(
            		WorkflowRouteParam.class, new Long(id));
        } catch (Exception e) {
            ExceptionUtil.throwCommonException(e.getMessage(),
                    ErrorCode.ERROR_CODE_WORKFLOW_ROUTE_PARAM_SELECT, e);
        }
        return null;
    }



    /**
     * 根据输入的条件查询所有符合条件的记录
     *
     * @param whereString
     * @param objArray
     * @param typeArray
     * @return 包含LimitParam对象的List
     * @throws CommonException
     */
    public List queryByCondition(String whereString, Object[] objArray,
            Type[] typeArray) throws CommonException {
        try {
            List list = this.getHibernateTemplate().find(
                    "from WorkflowRouteParam po where " + whereString, objArray);
            return list;
        } catch (Exception e) {
            ExceptionUtil.throwCommonException(e.getMessage(),
                    ErrorCode.ERROR_CODE_WORKFLOW_ROUTE_PARAM_SELECT, e);
        }
        return null;
    }

    /**
     * 根据输入的条件查询所有符合条件的记录
     *
     * @param whereString
     * @return 包含LimitParam对象的List
     * @throws CommonException
     */
    public List queryByCondition(String whereString) throws CommonException {
        try {
            List list = this.getHibernateTemplate().find(
                    "from WorkflowRouteParam po where " + whereString);
            return list;
        } catch (Exception e) {
            ExceptionUtil.throwCommonException(e.getMessage(),
                    ErrorCode.ERROR_CODE_WORKFLOW_ROUTE_PARAM_SELECT, e);
        }
        return null;
    }

    /**
     * 更新记录
     *
     * @param po
     * @throws CommonException
     */
    public void update(WorkflowRouteParam po) throws CommonException {
        try {
            this.getHibernateTemplate().update(po);
        } catch (Exception e) {
            ExceptionUtil.throwCommonException(e.getMessage(),
                    ErrorCode.ERROR_CODE_WORKFLOW_ROUTE_PARAM_UPDATE, e);
        }
    }

    /**
     * 插入记录
     *
     * @param po
     * @throws CommonException
     */
    public void insert(WorkflowRouteParam po) throws CommonException {
        try {
            this.getHibernateTemplate().save(po);
        } catch (Exception e) {
            ExceptionUtil.throwCommonException(e.getMessage(),
                    ErrorCode.ERROR_CODE_WORKFLOW_ROUTE_PARAM_INSERT, e);
        }
    }

    /**
     * 删除记录
     *
     * @param po
     * @throws CommonException
     */
    public void delete(WorkflowRouteParam po) throws CommonException {
        try {
            this.getHibernateTemplate().delete(po);
        } catch (Exception e) {
            ExceptionUtil.throwCommonException(e.getMessage(),
                    ErrorCode.ERROR_CODE_WORKFLOW_ROUTE_PARAM_DELETE, e);
        }
//    	 try {
//    		 delete(po.getId());
//         } catch (Exception e) {
//             ExceptionUtil.throwCommonException(e.getMessage(),
//                     ErrorCode.ERROR_CODE_WORKFLOW_ROUTE_PARAM_DELETE, e);
//         }
    }

    /**
     * 根据Hibernate ID删除记录
     *
     * @param id
     * @throws CommonException
     */
    public void delete(Integer id) throws CommonException {
        try {
            this.getHibernateTemplate().delete(query(id));
        } catch (Exception e) {
            ExceptionUtil.throwCommonException(e.getMessage(),
                    ErrorCode.ERROR_CODE_WORKFLOW_ROUTE_PARAM_DELETE, e);
        }
    }
}