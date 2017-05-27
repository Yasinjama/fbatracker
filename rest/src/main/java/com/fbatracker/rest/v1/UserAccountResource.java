package com.fbatracker.rest.v1;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fbatracker.model.UserAccount;
import com.fbatracker.repository.UserAccountRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value="User Account resource",produces = "application/json")
public class UserAccountResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserAccountResource.class);
	
	@Autowired
	UserAccountRepository repository;
	
	@GET
	@Path("v1/useraccount/{username}")
	@ApiOperation(value="Get a User Account resource. Version 1",response = UserAccount.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "User account found"),
			@ApiResponse(code = 404, message = "Given username not found")
	})
	public UserAccount getUserAccount(@ApiParam @PathParam("username")String username)
	{
		LOGGER.trace("getUserAccount username={}",username);
		UserAccount userAccount = repository.findUserAccountByUsername(username);
		return userAccount;
	}
}
