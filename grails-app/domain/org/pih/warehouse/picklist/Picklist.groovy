/**
* Copyright (c) 2012 Partners In Health.  All rights reserved.
* The use and distribution terms for this software are covered by the
* Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
* which can be found in the file epl-v10.html at the root of this distribution.
* By using this software in any fashion, you are agreeing to be bound by
* the terms of this license.
* You must not remove this notice, or any other, from this software.
**/ 
package org.pih.warehouse.picklist

import org.pih.warehouse.auth.AuthService;
import org.pih.warehouse.core.Comment;
import org.pih.warehouse.core.Document;
import org.pih.warehouse.core.Event;
import org.pih.warehouse.core.EventCode;
import org.pih.warehouse.core.EventType;
import org.pih.warehouse.core.Location;
import org.pih.warehouse.core.Person;
import org.pih.warehouse.core.User;
import org.pih.warehouse.fulfillment.Fulfillment;
import org.pih.warehouse.request.Request;
import org.pih.warehouse.request.RequestStatus;

class Picklist implements Serializable {
	
	def beforeInsert = {
		createdBy = AuthService.currentUser.get()
	}
	def beforeUpdate ={
		updatedBy = AuthService.currentUser.get()
	}
	
	String id
	String name
	String description 		// a user-defined, searchable name for the order 
	
	Request request 		
	Person picker
	Date datePicked
	
	// Audit fields
	Date dateCreated
	Date lastUpdated
	User createdBy
	User updatedBy
	
	static hasMany = [ picklistItems : PicklistItem ]
	static mapping = {
		id generator: 'uuid'
		picklistItems cascade: "all-delete-orphan", sort: "id"
	}
	
	static constraints = { 
		name(nullable:true)
		description(nullable:true)
		picker(nullable:false)
		datePicked(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		createdBy(nullable:true)
		updatedBy(nullable:true)
	}	
}