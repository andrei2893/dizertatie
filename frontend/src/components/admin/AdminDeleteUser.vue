<template>
    <div class="delete-user">
        <md-list  class="orange-bk">
            <md-list-item v-for="user of users" :key="user.id">
                <md-checkbox v-model="idsToBeDeleted" :value="user.id">
                </md-checkbox>
                <div class="row-item">
                    {{user.name}}
                </div>
                <div class="row-item">
                    {{user.email}}
                </div>
                <div>
                    {{user.role}}
                </div>
            </md-list-item>
            <md-button class="md-primary" @click="deleteUsers" :disabled="!idsToBeDeleted.length">
                delete
            </md-button>
        </md-list>
    </div>
</template>

<script>
import {findAllUsers,deleteAllUsers} from '@/api/user'
export default {
    data() {
        return {
            users: [],
            idsToBeDeleted: [],
        }
    },
    mounted() {
        findAllUsers().then((users) => this.users = users)
    },
    methods: {
        deleteUsers() {
            deleteAllUsers(this.idsToBeDeleted).then((ok) => {
                if (ok) {
                    findAllUsers().then((users) => {
                        this.users = users
                        this.idsToBeDeleted = []
                    })

                }
            })
        }
    }
}
</script>

<style>
.delete-user{
    width: 800px;

}
.row-item{
    width: 400px;

}
</style>
