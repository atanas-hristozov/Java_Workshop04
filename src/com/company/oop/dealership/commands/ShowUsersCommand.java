package com.company.oop.dealership.commands;

import com.company.oop.dealership.core.contracts.VehicleDealershipRepository;
import com.company.oop.dealership.models.contracts.User;
import com.company.oop.dealership.utils.ValidationHelpers;

import java.util.List;

public class ShowUsersCommand extends BaseCommand {
    private static final String NOT_ADMIN_ERR_MSG = "You are not an admin!";
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;
    public ShowUsersCommand(VehicleDealershipRepository vehicleDealershipRepository) {
        super(vehicleDealershipRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        return showUsers();
    }

    private String showUsers() {
        validatePrivilege();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("--USERS--\n");
        int i = 0;
        for(User user : getVehicleDealershipRepository().getUsers()) {
            i++;
            stringBuilder.append(String.format("%d. %s", i, user.print()));

            if(i < getVehicleDealershipRepository().getUsers().size()) {
                stringBuilder.append(String.format("\n"));
            }
        }
        return stringBuilder.toString();
    }

    private void validatePrivilege() {
        User currentUser = getVehicleDealershipRepository().getLoggedInUser();
        if(!currentUser.isAdmin()) {
            throw new IllegalArgumentException(NOT_ADMIN_ERR_MSG);
        }
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }
}
