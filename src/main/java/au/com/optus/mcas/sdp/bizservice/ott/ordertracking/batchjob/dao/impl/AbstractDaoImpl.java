/**
 * FileName      : $Id:$
 *
 * Copyright Notice: ©2004 Singapore Telecom Pte Ltd -- Confidential and Proprietary
 *
 * All rights reserved.
 * This software is the confidential and proprietary information of SingTel Pte Ltd
 * ("Confidential Information"). You shall not disclose such Confidential Information
 * and shall use it only in accordance with the terms of the license agreement you
 * entered into with SingTel.
 */
package au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.dao.impl;



import au.com.optus.mcas.core.persistence.domain.dao.jpa.AbstractGenericDaoImpl;
import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.dao.OrderTrackingJobGenericDao;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
/**
 * Default implementation of the {@link OrderTrackingJobGenericDao} Data Access Object.
 *
 * @version $Rev: $
 * @param <E> The persistent type
 */
public abstract class AbstractDaoImpl<E>  extends AbstractGenericDaoImpl<E>
    implements OrderTrackingJobGenericDao<E> {
       /**
     * Default constructor for instances of {@link AbstractDaoImpl}. This constructor will try to automatically
     * infer the generics type of this DAO class.
     */
    public AbstractDaoImpl() {
        this(null);
    }

    /**
     * Constructs a new instance of {@link AbstractDaoImpl} for the specified repository type.  If the specified
     * type is <code>null</code>, this constructor will try to automatically infer the generics type of this
     * DAO class.
     *
     * @param entityClass The persistent type that this DAO is for as a {@link Class}
     */
    public AbstractDaoImpl(final Class<E> entityClass) {
        super(entityClass);
    }

    /**
     * {@inheritDoc}
     */
    public void saveOrUpdateEntity(E entity) {
        getHibernateSession().saveOrUpdate(entity);
    }

    /**
     * {@inheritDoc}
     */
    public DetachedCriteria createDetachedCriteria() {
        return DetachedCriteria.forClass(getEntityClass());
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<E> findByCriteria(final DetachedCriteria criteria) {
        Assert.notNull(criteria,
            "Detached criterial cannot be null in call to findByCriteria()");

        if (getLogger().isDebugEnabled()) {
            getLogger().debug(String.format(
                    "Finding instances of '%s' using detached criteria: %s",
                    getEntityClass(), criteria));
        }

        final Criteria executableCriteria = criteria.getExecutableCriteria(getHibernateSession());
        return executableCriteria.list();
    }


    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<E> findByCriteria(final DetachedCriteria criteria,
        final int firstResult, final int maxResults) {
        Assert.notNull(criteria,
            "Detached criterial cannot be null in call to findByCriteria()");

        if (getLogger().isDebugEnabled()) {
            getLogger().debug(String.format(
                    "Finding instances of '%s' using detached criteria: %s [firstResult = %d, maxResults = %d]",
                    getEntityClass(), criteria, firstResult, maxResults));
        }

        final Criteria executableCriteria = criteria.getExecutableCriteria(getHibernateSession());

        if (firstResult >= 0) {
            executableCriteria.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            executableCriteria.setMaxResults(maxResults);
        }

        return executableCriteria.list();
    }

    /**
     * {@inheritDoc}
     */
    public Object updateByNativeQuery(final String sqlQuery) {
        SQLQuery q = getHibernateSession().createSQLQuery(sqlQuery);
        int count = q.executeUpdate();

        return count;
    }

    /**
     * {@inheritDoc}
     */
    public Object findByNativeQuery(final String sqlQuery) {
        SQLQuery q = getHibernateSession().createSQLQuery(sqlQuery);
        return q.list();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<E> findByNamedQueryAndNamedParams(final String queryName,
            final Map<String, ? extends Object> parameters,
        int limit) {
        if (getLogger().isDebugEnabled()) {
            getLogger().debug(String.format(
                    "Finding instances of '%s' using named query '%s' and the following parameter/value(s): %s",
                    getEntityClass(), queryName, parameters));
        }

        final Query namedQuery = getEntityManager().createNamedQuery(queryName);
        // final TypedQuery<E> namedQuery = mEntityManager.createNamedQuery(queryName, mEntityClass);
        if (!CollectionUtils.isEmpty(parameters)) {
            for (final Map.Entry<String, ? extends Object> param : parameters.entrySet()) {
                namedQuery.setParameter(param.getKey(), param.getValue());
            }
        }
        namedQuery.setFirstResult(0);
        namedQuery.setMaxResults(limit);

        return namedQuery.getResultList();
    }

    /**
     * Returns an associated hibernate session via JPA Entity Manager.
     * @return Session
     */
    protected Session getHibernateSession() {
        return (Session) getEntityManager().getDelegate();
    }


}
