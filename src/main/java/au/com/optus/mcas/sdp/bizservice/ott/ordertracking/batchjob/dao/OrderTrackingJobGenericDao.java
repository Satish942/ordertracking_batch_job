/**
 * FileName      : $Id: OrderTrackingJobGenericDao.java $
 *
 * Copyright Notice: ©2004 Singapore Telecom Pte Ltd -- Confidential and Proprietary
 *
 * All rights reserved.
 * This software is the confidential and proprietary information of SingTel Pte Ltd
 * ("Confidential Information"). You shall not disclose such Confidential Information
 * and shall use it only in accordance with the terms of the license agreement you
 * entered into with SingTel.
 */
package au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.dao;


import au.com.optus.mcas.core.persistence.domain.dao.jpa.GenericDao;
import java.util.List;
import java.util.Map;
import org.hibernate.criterion.DetachedCriteria;

/**
 * Describes behaviour common to all Reporting repository classes.
 *
 * @param <E> The persistent type
 * @version $Rev:$
 */
public interface OrderTrackingJobGenericDao<E> extends GenericDao<E> {
    /**
     * saves or updates the entity to DB.
     * @param entity
     *        E
     *
     *
     */
    void saveOrUpdateEntity(E entity);



    /**
     * Create a new instance of {@link DetachedCriteria}, for the underlying entity class which can then be used
     * to perform criteria queries via {@link #findByCriteria(DetachedCriteria)}.
     *
     * @return A Criteria in "detached mode" that can be used without access to the underlying Hibernate session
     */
    DetachedCriteria createDetachedCriteria();

    /**
     * Executes a query based on the given detached Hibernate criteria object.
     *
     * @param criteria the detached Hibernate criteria object
     * @return a {@link List} containing 0 or more persistent instances
     */
    List<E> findByCriteria(DetachedCriteria criteria);

    /**
     * Executes a query based on the given detached Hibernate criteria object.
     *
     * @param criteria the detached Hibernate criteria object
     * @param firstResult the index of the first result object to be retrieved
     *        (numbered from 0)
     * @param maxResults the maximum number of result objects to retrieve
     *        (or <=0 for no limit)
     * @return a {@link List} containing 0 or more persistent instances
     */
    List<E> findByCriteria(DetachedCriteria criteria, int firstResult,
        int maxResults);

    /**
     * Executes a native query for update operation and returns the number of records updated.
     * @param sqlQuery
     *        String
     * @return Object.
     */
    Object updateByNativeQuery(final String sqlQuery);

    /**
     * Executes a native query for select operation.
     * This can be used for all special queries that named queries cannot execute
     * @param sqlQuery
     *        String
     * @return Object.
     */
    Object findByNativeQuery(final String sqlQuery);

    /**
     * Execute a named query, binding a number of values to ":" named parameters in the query string.
     * <p>
     * A named query is defined in either a mapping file or within a domain entity class.
     *
     * @param queryName the name of a Hibernate query in a mapping file
     * @param parameters the values of the parameters
     * @param limit the query Fetch limit to restrict the no. of records searched.
     * @return a {@link List} containing the results of the query execution
     */
    List<E> findByNamedQueryAndNamedParams(String queryName,
        Map<String, ?extends Object> parameters, int limit);
}
