package com.tinellus.multischema.config;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.cfg.AvailableSettings;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.stereotype.Component;

@Component
public class ConnectionProvider implements MultiTenantConnectionProvider, HibernatePropertiesCustomizer{
    
	@Autowired DataSource dataSource;
	
	@Override
	public boolean isUnwrappableAs(Class unwrapType) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> T unwrap(Class<T> unwrapType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Connection getAnyConnection() throws SQLException {
		// TODO Auto-generated method stub
		return getConnection("PUBLIC");
	}

	@Override
	public void releaseAnyConnection(Connection connection) throws SQLException {
		connection.close();		
	}

	@Override
	public Connection getConnection(String schema) throws SQLException {
		
		final Connection connection = dataSource.getConnection();
		connection.setSchema(schema);
		return connection;
	}

	@Override
	public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
		connection.setSchema("PUBLIC");
		connection.close();		
	}

	@Override
	public boolean supportsAggressiveRelease() {
		// TODO Auto-generated method stub
		return false;
	}
	

	@Override
	public void customize(Map<String, Object> hibernateProperties) {
		hibernateProperties.put(AvailableSettings.MULTI_TENANT_CONNECTION_PROVIDER, this);
	}

}
