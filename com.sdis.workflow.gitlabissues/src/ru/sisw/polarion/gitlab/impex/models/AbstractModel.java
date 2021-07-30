package ru.sisw.polarion.gitlab.impex.models;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class AbstractModel {
	private final PropertyChangeSupport m_propertyChangeSupport = new PropertyChangeSupport(this);
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		m_propertyChangeSupport.addPropertyChangeListener(listener);
	}
	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		m_propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	}
	protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
		m_propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
	}
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		m_propertyChangeSupport.removePropertyChangeListener(listener);
	}
	public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		m_propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
	}
}