<template>
    <div style="margin: 20px;">
        <div>
            <Row style="margin-bottom: 25px;">
                <Col span="8">学生姓名：
                    <Input v-model="studentReq" placeholder="请输入..." style="width:200px"></Input>
                </Col>
                <Col span="8">
                    <Button type="primary" shape="circle" icon="ios-search" @click="searchStudent()">搜索</Button>
                </Col>
            </Row>
        </div>
        <div>
            <ul>
                <li>
                    <div style="padding: 10px 0;">
                        <Table border :columns="columns1" :data="data1"></Table>
                    </div>
                </li>
            </ul>
        </div>
        <div>
            <Row style="margin-bottom: 25px;">
                <Col span="8">课程名称：
                    <Input v-model="courseReq" placeholder="请输入..." style="width:200px"></Input>
                </Col>
                <Col span="8">
                    <Button type="primary" shape="circle" icon="ios-search" @click="searchCourse()">搜索</Button>
                </Col>
                <Button type="primary" icon="plus-round" @click="openNewModal()">报名</Button>
            </Row>
        </div>
        <div>
            <ul>
                <li>
                    <div style="padding: 10px 0;">
                        <Table border :columns="columns2" :data="data2"></Table>
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
               title="报名" @on-ok="newOk('newRegister')" @on-cancel="cancel()">
            <Form ref="newRegister" :model="newRegister" :label-width="80">
                <Row>
                    <Col span="12">
                        <Form-item label="学生:" prop="studentName">
                            <Input type="text" v-model="newRegister.studentName" style="width: 200px" readonly="true"/>
                        </Form-item>
                    </Col>
                    <Col span="12">
                        <Form-item label="课程:" prop="courseName">
                            <Input type="text" v-model="newRegister.courseName" style="width: 200px" readonly="true"/>
                        </Form-item>
                    </Col>
                    <Col span="12">
                        <Form-item label="报名次数:" prop="times">
                            <Input type="text" v-model="newRegister.times" style="width: 200px"/>
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
                studentReq: null,
                courseReq: null,
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
                SearchcourseName: null,
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
                rigister: {
                    courseId: null,
                    studentId: null,
                    payStatus: null,
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
                },
                newRegister: {
                    courseId: null,
                    studentId: null,
                    times: null,
                    studentName: null,
                    courseName: null
                },
                newRegisterReq: {
                    courseId: null,
                    studentId: null,
                    times: null,
                    studentName: null,
                    courseName: null
                },
                /*表显示字段*/
                columns1: [
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
                        title: '其他',
                        key: 'remark'
                    },
                ],
                /*表数据*/
                data1: [],
                /*表显示字段*/
                columns2: [
                    {
                        // type: 'selection',
                        width: 60,
                        align: 'center',
                        render: (h, params) => {
                            return h('div', [
                                h('Checkbox', {
                                    props: {
                                        value: params.row.checkBox,
                                    },
                                    on: {
                                        'on-change': () => {
                                            this.initnewRegister();
                                            // this.data2.forEach((items)=>{      //先取消所有对象的勾选，checkBox设置为false
                                            //     this.$set(items,'checkBox',false);
                                            // });
                                            // this.data2[params.index].checkBox = e;  //再将勾选的对象的checkBox设置为true
                                            this.newRegister.courseId = params.row.id;
                                            this.newRegister.courseName = params.row.name;
                                        }
                                    }
                                })
                            ]);
                        }
                    },
                    {
                        title: 'id',
                        key: 'id'
                    },
                    {
                        title: '课程编码',
                        key: 'code'
                    },
                    {
                        title: '课程名称',
                        key: 'name'
                    },
                    {
                        title: '校区',
                        key: 'areaDescription'
                    },
                    {
                        title: '年度',
                        key: 'year'
                    },
                    {
                        title: '进度',
                        key: 'progressDescription'
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
                "loginName": this.loginName
            });
            this.getCouses({
                "pageInfo": this.pageInfo,
                "loginName": this.loginName
            });

        },
        methods: {
            /*pageInfo实体初始化*/
            initPageInfo() {
                this.pageInfo.page = 0;
                this.pageInfo.pageSize = 10;
            },
            initnewRegister() {
                this.newRegister.courseId = null;
                this.newRegister.courseName = null;
                this.newRegister.times = null;
                // this.newRegister.studentId = null;
                // this.newRegister.studentName = null;
            },
            initReq() {
                this.newRegisterReq.courseId = null;
                this.newRegisterReq.courseName = null;
                this.newRegisterReq.studentId = null;
                this.newRegisterReq.times = null;
                this.newRegisterReq.studentName = null;

            },
            /*得到表数据*/
            getStudent(e) {
                this.axios({
                    method: 'get',
                    url: '/student',
                    params: {
                        'studentName': this.studentReq
                    }
                }).then(function (response) {
                    this.data1 = response.data;
                    this.studentReq = null;
                }.bind(this)).catch(function (error) {
                    alert(error);
                    this.studentReq = null;
                });
            },
            getCourse(e) {
                this.axios({
                    method: 'get',
                    url: '/courseManager/course',
                    params: {
                        'courseName': this.courseReq
                    }
                }).then(function (response) {
                    this.data2 = response.data.list;
                    this.total = response.data.total;
                    this.courseReq = null;
                }.bind(this)).catch(function (error) {
                    alert(error);
                    this.courseReq = null;
                });
            },
            getTable(e) {
                // this.axios({
                //     method: 'get',
                //     url: '/student',
                //     params: {
                //         // 'page':e.pageInfo.page,
                //         // 'pageSize':e.pageInfo.pageSize,
                //         // 'studentName':e.studentName
                //         'studentName': '王大傻'
                //     }
                // }).then(function (response) {
                //     this.data1 = response.data;
                // }.bind(this)).catch(function (error) {
                //     alert(error);
                // });
            },
            getCouses(e) {
                this.axios({
                    method: 'get',
                    url: '/courseManager/course',
                    params: {
                        'pageNo': e.pageInfo.page,
                        'pageSize': e.pageInfo.pageSize
                        // 'loginName':e.loginName
                    }
                }).then(function (response) {
                    this.data2 = response.data.list;
                    this.total = response.data.total;
                }.bind(this)).catch(function (error) {
                    alert(error);
                });
            },
            /*搜索按钮点击事件*/
            searchStudent(e) {
                this.initPageInfo();
                this.getStudent(e)({
                    "loginName": this.loginName
                });
                this.studentReq = null;
            },
            searchCourse() {
                this.getCourse()({
                    "loginName": this.loginName
                });
            },
            /*分页点击事件*/
            pageSearch(e) {
                this.pageInfo.page = e;
                this.getCouses({
                    "pageInfo": this.pageInfo,
                    "loginName": this.loginName
                });
            },
            /*新建点击触发事件*/
            openNewModal(e) {
                // this.newRegister.courseId =null;
                // this.newRegister.courseName =null;
                this.newRegister.studentId = this.data1[0].id;
                this.newRegister.studentName = this.data1[0].name;
                this.newModal = true;
            },
            /*新建modal的newOk点击事件*/
            newOk(newRegister) {
                this.initReq();
                this.newRegisterReq.studentName = this.newRegister.studentName;
                this.newRegisterReq.times = this.newRegister.times;
                this.newRegisterReq.studentId = this.newRegister.studentId;
                this.newRegisterReq.courseName = this.newRegister.courseName;
                this.newRegisterReq.courseId = this.newRegister.courseId;
                console.log(this.newRegister.courseId);
                console.log(this.newRegisterReq.courseId);
                this.axios({
                    method: 'post',
                    url: '/course/register/' + this.newRegisterReq.courseId,
                    contentType: 'form-data',
                    data: this.newRegisterReq
                }).then(function (response) {
                    // this.initUserNew();
                    this.initReq();
                    this.getTable({
                        "pageInfo": this.pageInfo,
                        "loginName": this.loginName
                    });
                    this.$Message.info('新建成功');
                }.bind(this)).catch(function (error) {
                    alert(error);
                });
                this.newModal = false;

            }
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
    }
</script>