#include "UserService.hpp"

#include "UserErrors.hpp"

class UserServiceImpl : public UserService {
public:
    either<Error, User> getUser(int64_t id) override;
};

std::shared_ptr<UserService> UserService::getService() {
    static auto s_service = std::make_shared<UserServiceImpl>();
    return s_service;
}

either<Error, User> UserServiceImpl::getUser(int64_t id) {
    if (id == 1234LL) {
        return User{id, "John Smith"};
    }
    else {
        return Error{UserErrors::DOMAIN, UserErrors::USERNOTFOUND, "User not found"};
    }
}
