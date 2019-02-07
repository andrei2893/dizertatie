<template>
    <div>
        <md-list>
            <md-list-item v-for="user of users" :key="user.id">
                <md-checkbox v-model="idsToBeDeleted" :value="user.id">
                    {{user.name}}
                </md-checkbox>
                <span>
                    {{user.email}}
                </span>
            </md-list-item>
            <md-list-item>
                <md-button class="md-primary" @click="deleteUsers" :disabled="!idsToBeDeleted.length">
                    delete
                    </md-button>
            </md-list-item>
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

</style>
