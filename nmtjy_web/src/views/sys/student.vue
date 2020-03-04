<template>
    <div style="margin: 20px;">
        <div>
            <Row style="margin-bottom: 25px;">
                <Col span="8">课程信息：
                    <Input v-model="loginName" placeholder="请输入..." style="width:200px"></Input>
                </Col>
                <Col span="8">
                    <Button type="primary" shape="circle" icon="ios-search" @click="search()">搜索</Button>
                </Col>
            </Row>
        </div>
        <div>
            <ul>
                <li>
                    <Button type="primary" icon="plus-round" @click="openNewModal()">新建</Button>
                    <Button type="success" icon="wrench" @click="openModifyModal()">修改</Button>
                    <!--<Button type="error" icon="trash-a" @click="del()">删除</Button>-->
                </li>
                <li>
                    <div style="padding: 10px 0;">
                        <Table border :columns="columns1" :data="data1" :height="400"
                               @on-selection-change="s=>{change(s)}" @on-row-dblclick="s=>{dblclick(s)}"></Table>
                    </div>
                </li>
                <li>
                    <div style="text-align: right;">
                        <Page :total="total" :page-size="pageInfo.pageSize" show-elevator show-total
                              @on-change="e=>{pageSearch(e)}"></Page>
                    </div>
                </li>
            </ul>
        </div>
        <!--添加modal-->
        <Modal :mask-closable="false" :visible.sync="newModal" :loading="loading" v-model="newModal" width="600"
               title="新建" @on-ok="newOk('newStudent')" @on-cancel="cancel()">
            <Form ref="newStudent" :model="newStudent" :label-width="80">
                <Row>
                    <Col span="12">
                        <Form-item label="姓名:" prop="name">
                            <Input v-model="newStudent.name" style="width: 200px"/>
                        </Form-item>
                    </Col>
                    <Col span="12">
                        <Form-item label="年级:" prop="grade">
                            <Input v-model="newStudent.grade" style="width: 200px"/>
                        </Form-item>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <Form-item label="小学:" prop="elementarySchool">
                            <Input v-model="newStudent.elementarySchool" style="width: 200px"/>
                        </Form-item>
                    </Col>
                    <Col span="12">
                        <Form-item label="中学:" prop="middleSchool">
                            <Input v-model="newStudent.middleSchool" style="width: 200px"/>
                        </Form-item>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <Form-item label="高中:" prop="highSchool">
                            <Input v-model="newStudent.highSchool" style="width: 200px"/>
                        </Form-item>
                    </Col>
                    <Col span="12">
                        <Form-item label="生日:" prop="birthday">
                            <Input v-model="newStudent.birthday " style="width: 200px"/>
                        </Form-item>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <Form-item label="备注:" prop="remark">
                            <Input v-model="newStudent.remark" style="width: 200px"/>
                        </Form-item>
                    </Col>
                </Row>
            </Form>
        </Modal>
        <!--修改modal-->
        <Modal :mask-closable="false" :visible.sync="modifyModal" :loading="loading" v-model="modifyModal" width="600"
               title="修改" @on-ok="modifyOk('userModify')" @on-cancel="cancel()">
            <Form ref="userModify" :model="userModify" :label-width="80">
                <Row>
                    <Col span="12">
                        <Form-item label="登录名:" prop="loginName">
                            <Input v-model="userModify.loginName" style="width: 204px"/>
                        </Form-item>
                    </Col>
                    <Col span="12">
                        <Form-item label="用户名:" prop="name">
                            <Input v-model="userModify.name" style="width: 204px"/>
                        </Form-item>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <Form-item label="密码:" prop="password">
                            <Input v-model="userModify.password" type="password" style="width: 204px"/>
                        </Form-item>
                    </Col>
                </Row>
                <!--<Row>-->
                <!--<Col span="12">-->
                <!--<Form-item label="邮箱:" prop="email">-->
                <!--<Input v-model="userModify.email" style="width: 204px"/>-->
                <!--</Form-item>-->
                <!--</Col>-->
                <!--</Row>-->
            </Form>
        </Modal>
        <!--详情modal-->
        <Modal v-model="detailModal" width="1000" title="详情" @on-ok="roleOk()" @on-cancel="cancel()">
            <div>
                <Table border :columns="columns2" :data="data2" :height="260"
                       @on-selection-change="s=>{change2(s)}"></Table>
            </div>
        </Modal>
        <!--报名modal-->
        <Modal :mask-closable="false" :visible.sync="rigisterModal" :loading="loading" v-model="rigisterModal"
               width="600"
               title="报名" @on-ok="newOk('newStudent')" @on-cancel="cancel()">
            <Form ref="newRegister" :model="newRegister" :label-width="80">
                <Row>
                    <Col span="12">
                        <Form-item label="姓名:" prop="name">
                            <Input v-model="newRegister.studentName" style="width: 200px"/>
                        </Form-item>
                    </Col>
                    <Col span="12">
                        <Form-item label="年级:" prop="grade">
                            <Input v-model="newStudent.grade" style="width: 200px"/>
                        </Form-item>
                    </Col>
                </Row>
            </Form>
        </Modal>

    </div>
</template>
<script>
    export default {
        data: function () {
            return {
                /*用于查找的登录名*/
                loginName: null,
                /*选择的数量*/
                count: null,
                /*选中的组数据*/
                groupId: null,
                /*新建modal的显示参数*/
                newModal: false,
                /*修改modal的显示参数*/
                modifyModal: false,
                /*详情页modal的显示参数*/
                detailModal: false,
                rigisterModal: false,
                /*分页total属性绑定值*/
                total: 0,
                /*loading*/
                loading: true,
                /*pageInfo实体*/
                pageInfo: {
                    page: 1,
                    pageSize: 10
                },
                student: {
                    id: null,
                    name: null,
                    elementarySchool: null,
                    middleSchool: null,
                    highSchool: null,
                    grade: null,
                    remark: null,
                    birthday: null
                },
                newStudent: {
                    id: null,
                    name: null,
                    grade: null,
                    birthday: null,
                    elementarySchool: null,
                    highSchool: null,
                    middleSchool: null,
                    remark: null
                },
                newRegister: {
                    courseId: null,
                    studentId: null,
                    payStatus: null,
                    studentName: null,
                    times: null,
                    remark: null
                },
                payment: {
                    courseId: null,
                    studentId: null,
                    times: null,
                    discount: null,
                    materialPay: null,
                    otherPay: null,
                    remark: null
                }
                ,
                /*表显示字段*/
                columns1: [
                    {
                        type: 'selection',
                        width: 60,
                        align: 'center'
                    },
                    {
                        title: 'id',
                        key: 'id'
                    },
                    {
                        title: '学生姓名',
                        key: 'name'
                    },
                    {
                        title: '年级',
                        key: 'grade'
                    },
                    {
                        title: '小学',
                        key: 'elementarySchool'
                    },
                    {
                        title: '中学',
                        key: 'middleSchool'
                    },
                    {
                        title: '高中',
                        key: 'highSchool'
                    },
                    {
                        title: '生日',
                        key: 'birthday'
                    },
                    {
                        title: '其他',
                        key: 'remark'
                    },
                    {
                        title: '操作',
                        align: 'center',
                        key: 'action',
                        render: (h, params) => {
                            return h('div', [
                                h('Button', {
                                    props: {
                                        type: 'info',
                                    },
                                    on: {
                                        click: () => {
                                            this.torigister(params.row);
                                        }
                                    }
                                }, '报名')
                            ]);
                        }
                    },
                ],
                /*表数据*/
                data1: [],
                /*表显示字段*/
                columns2: [
                    {
                        title: '课程名称',
                        width: 100,
                        key: 'name'
                    },
                    {
                        title: '课程类型',
                        width: 100,
                        key: 'typeDescription'
                        // key: 'type'
                    }
                ],
                /*表数据*/
                data2: [],
                userModify: {
                    id: null,
                    name: null,
                    loginName: null,
                    password: null,
                    email: null
                }
                /*data2的临时存储*/
            }
        },
        mounted() {
            /*页面初始化调用方法*/
            this.getTable({
                "pageInfo": this.pageInfo,
                "loginName": this.loginName
            });
            // this.axios({
            //     method: 'get',
            //     // url: '/roles/all'
            //     url: '/test'
            // }).then(function (response) {
            //     this.data2Temp = response.data;
            // }.bind(this)).catch(function (error) {
            //     alert(error);
            // });
        },
        methods: {
            /*pageInfo实体初始化*/
            initPageInfo() {
                this.pageInfo.page = 0;
                this.pageInfo.pageSize = 10;
            },
            initNewStudent() {
                this.newStudent.id = null;
                this.newStudent.birthday = null;
                this.newStudent.name = null;
                this.newStudent.elementarySchool = null;
                this.newStudent.middleSchool = null;
                this.newStudent.highSchool = null;
                this.newStudent.grade = null;
                this.newStudent.remark = null;
            },
            /*得到表数据*/
            getTable(e) {
                this.axios({
                    method: 'get',
                    url: '/student/list',
                    params: {
                        'page': e.pageInfo.page,
                        'pageSize': e.pageInfo.pageSize,
                        // 'loginName':e.loginName
                    }
                }).then(function (response) {
                    this.data1 = response.data;
                    this.total = response.data.totalCount;
                }.bind(this)).catch(function (error) {
                    alert(error);
                });
            },
            /*搜索按钮点击事件*/
            search() {
                this.initPageInfo();
                this.getTable({
                    "pageInfo": this.pageInfo,
                    "loginName": this.loginName
                });
            },
            /*分页点击事件*/
            pageSearch(e) {
                this.pageInfo.page = e - 1;
                this.getTable({
                    "pageInfo": this.pageInfo,
                    "loginName": this.loginName
                });
            },
            /*新建点击触发事件*/
            openNewModal() {
                this.newModal = true;
                this.initNewStudent();
                this.count = 0;
                this.groupId = null;
            },
            /*新建modal的newOk点击事件*/
            newOk(newStudent) {
                this.$refs[newStudent].validate((valid) => {
                    if (valid) {
                        // this.initUser();
                        // this.userSet(this.userNew);
                        this.axios({
                            method: 'post',
                            url: '/student/create',
                            data: this.newStudent
                        }).then(function (response) {
                            // this.initUserNew();
                            this.initNewStudent();
                            this.getTable({
                                "pageInfo": this.pageInfo,
                                "loginName": this.loginName
                            });
                            this.$Message.info('新建成功');
                        }.bind(this)).catch(function (error) {
                            alert(error);
                        });
                        this.newModal = false;
                    } else {
                        this.$Message.error('表单验证失败!');
                        setTimeout(() => {
                            this.loading = false;
                            this.$nextTick(() => {
                                this.loading = true;
                            });
                        }, 1000);
                    }
                });
            },
            /*点击修改时判断是否只选择一个*/
            openModifyModal() {
                if (this.count > 1 || this.count < 1) {
                    this.modifyModal = false;
                    this.$Message.warning('请至少选择一项(且只能选择一项)');
                } else {
                    this.modifyModal = true;
                }
            },
            /*修改modal的modifyOk点击事件*/
            modifyOk(userModify) {
                this.$refs[userModify].validate((valid) => {
                    if (valid) {
                        this.initUser();
                        this.userSet(this.userModify);
                        this.axios({
                            method: 'put',
                            url: '/users/' + this.user.id,
                            data: this.user
                        }).then(function (response) {
                            this.initUserNew();
                            this.getTable({
                                "pageInfo": this.pageInfo,
                                "loginName": this.loginName
                            });
                            this.$Message.info('修改成功');
                        }.bind(this)).catch(function (error) {
                            alert(error);
                        });
                        this.modifyModal = false;
                    } else {
                        this.$Message.error('表单验证失败!');
                        setTimeout(() => {
                            this.loading = false;
                            this.$nextTick(() => {
                                this.loading = true;
                            });
                        }, 1000);
                    }
                })
            },
            /*modal的cancel点击事件*/
            cancel() {
                this.$Message.info('点击了取消');
            },
            /*table选择后触发事件*/
            change(e) {
                if (e.length == 1) {
                    this.userModifySet(e['0']);
                }
                this.setGroupId(e);
            },
            /*通过选中的行设置groupId的值*/
            setGroupId(e) {
                this.groupId = [];
                this.count = e.length;
                for (var i = 0; i <= e.length - 1; i++) {
                    this.groupId.push(e[i].id);
                }
            },
            /*删除table中选中的数据*/
            del() {
                // if (this.groupId != null && this.groupId != "") {
                //     this.axios({
                //         method: 'delete',
                //         url: '/users',
                //         data: this.groupId
                //     }).then(function (response) {
                //         this.getTable({
                //             "pageInfo": this.pageInfo,
                //             "loginName": this.loginName
                //         });
                //         this.groupId = null;
                //         this.count = 0;
                //         this.$Message.info('删除成功');
                //     }.bind(this)).catch(function (error) {
                //         alert(error);
                //     });
                // }
            },
            /*表格中双击事件*/
            dblclick(e) {
                this.userModifySet(e);
                this.modifyModatrue;
                this.data1.sort();
            },
            initNewRegister() {
                this.newRegister.studentName = null;
                this.newRegister.studentId = null;
                this.newRegister.courseId = null;
                this.newRegister.times = null;
                this.newRegister.courseName = null;
            },
            /*点击详情*/
            torigister(e) {
                this.initNewRegister();
                this.rigisterModal = true;
                this.newRegister.studentName = e.name;
            },
            /*详情modal确认按钮点击事件*/
            roleOk() {
                // if (this.relationList != null) {
                //     this.axios({
                //         method: 'post',
                //         url: '/relations',
                //         data: this.relationList
                //     }).then(function (response) {
                //         this.$Message.info('配置成功');
                //     }.bind(this)).catch(function (error) {
                //         alert(error);
                //     });
                //     this.relationList = null;
                // }
            },
            /*详情modal中表选择改变事件*/
            change2(e) {
                // this.relationList = [];
                // if (e.length == 0) {
                //     this.relationList.push({
                //         "userId": this.data2[0].userId
                //     });
                // }
                // for (var i in e) {
                //     this.relationList.push({
                //         "userId": e[i].userId,
                //         "roleId": e[i].id
                //     });
                // }
            }
        }
    }
</script>