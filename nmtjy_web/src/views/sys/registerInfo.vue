<template>
    <div style="margin: 20px;">
        <div>
            <Row style="margin-bottom: 25px;">
                <Col span="8">课程信息：
                    <Input v-model="courseNamereq" placeholder="请输入..." style="width:200px"></Input>
                </Col>
                <Col span="8">学生姓名：
                    <Input v-model="studentNameReq" placeholder="请输入..." style="width:200px"></Input>
                </Col>
                <Col span="8">
                    <Button type="primary" shape="circle" icon="ios-search" @click="search()">搜索</Button>
                </Col>
            </Row>
        </div>
        <div>
            <ul>
                <li>
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
               title="新建" @on-ok="newOk('newCourse')" @on-cancel="cancel()">
            <Form ref="newCourse" :model="newCourse" :label-width="80">
                <Row>
                    <Col span="12">
                        <Form-item label="课程名称:" prop="name">
                            <Input v-model="newCourse.name" style="width: 200px"/>
                        </Form-item>
                    </Col>
                    <Col span="12">
                        <Form-item label="年度:" prop="year">
                            <Input v-model="newCourse.year" style="width: 200px"/>
                        </Form-item>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <Form-item label="季节班次:">
                            <!--<Select :label-in-value= "true" v-model="configSelect" style="width:200px" @on-change = "e=>{selectSeason(e)}">-->
                            <Select :label-in-value="true" v-model="selectSeason" style="width:200px"
                                    @on-change="e=>{newCourse.season=e.value}">
                                <Option v-for="item in dataCourseSeason" :value="item.value" :key="item.value">
                                    {{ item.description }}
                                </Option>
                            </Select>
                        </Form-item>
                    </Col>
                    <Col span="12">
                        <Form-item label="课程类型:">
                            <Select :label-in-value="true" v-model="selectType" style="width:200px"
                                    @on-change="e=>{this.newCourse.type=e.value}">
                                <Option v-for="item in dataCourseType" :value="item.value" :key="item.value">
                                    {{ item.description }}
                                </Option>
                            </Select>
                        </Form-item>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <Form-item label="所在校区:">
                            <!--<Select :label-in-value= "true" v-model="configSelect" style="width:200px" @on-change = "e=>{selectSeason(e)}">-->
                            <Select :label-in-value="true" v-model="selectArea" style="width:200px"
                                    @on-change="e=>{this.newCourse.area=e.value}">
                                <Option v-for="item in dataCourseArea" :value="item.value" :key="item.value">
                                    {{ item.description }}
                                </Option>
                            </Select>
                        </Form-item>
                    </Col>
                    <Col span="12">
                        <Form-item label="学校类型:">
                            <Select :label-in-value="true" v-model="selectSchoolType" style="width:200px"
                                    @on-change="e=>{this.newCourse.schoolType=e.value}">
                                <Option v-for="item in dataCourseSchoolType" :value="item.value" :key="item.value">
                                    {{ item.description }}
                                </Option>
                            </Select>
                        </Form-item>
                    </Col>
                </Row>

                <Row>
                    <Col span="12">
                        <Form-item label="课程进度:">
                            <!--<Select :label-in-value= "true" v-model="configSelect" style="width:200px" @on-change = "e=>{selectSeason(e)}">-->
                            <Select :label-in-value="true" v-model="selectProgress" style="width:200px"
                                    @on-change="e=>{newCourse.progress=e.value}">
                                <Option v-for="item in dataCourseProgress" :value="item.value" :key="item.value">
                                    {{ item.description }}
                                </Option>
                            </Select>
                        </Form-item>
                    </Col>
                    <Col span="12">

                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <Form-item label="上课时间:" prop="courseTime">
                            <Input v-model="newCourse.courseTime" style="width: 200px"/>
                        </Form-item>
                    </Col>
                    <Col span="12">
                        <Form-item label="每节课时间:" prop="perTime">
                            <Input v-model="newCourse.perTime" style="width: 200px"/>
                        </Form-item>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <Form-item label="目标人群:" prop="targetGroup">
                            <Input v-model="newCourse.targetGroup" type="text" style="width: 200px"/>
                        </Form-item>
                    </Col>
                    <Col span="12">
                        <Form-item label="课程次数:" prop="times">
                            <Input v-model="newCourse.times" type="text" style="width: 200px"/>
                        </Form-item>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <Form-item label="开课日期:" prop="startDate">
                            <Input v-model="newCourse.startDate" type="date" style="width: 200px"/>
                        </Form-item>
                    </Col>
                    <Col span="12">
                        <Form-item label="结课日期:" prop="endDate">
                            <Input v-model="newCourse.endDate" type="date" style="width: 200px"/>
                        </Form-item>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <Form-item label="单课费用:" prop="startDate">
                            <Input v-model="newCourse.perPrice" type="text" style="width: 200px"/>
                        </Form-item>
                    </Col>
                    <Col span="12">
                        <Form-item label="材料费:" prop="endDate">
                            <Input v-model="newCourse.materialPay" type="text" style="width: 200px"/>
                        </Form-item>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <Form-item label="课程描述:" prop="description">
                            <Input v-model="newCourse.description" type="textarea" style="width: 450px"/>
                        </Form-item>
                    </Col>
                </Row>

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
        <Modal v-model="registerModal" width="1000" title="报名" @on-ok="roleOk()" @on-cancel="cancel()">
            <div>
                <Table border :columns="columns2" :data="data2" :height="260"
                       @on-selection-change="s=>{change2(s)}"></Table>
            </div>
        </Modal>
    </div>
</template>
<script>
    export default {
        data: function () {
            return {
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
                /*分页total属性绑定值*/
                total: 0,
                studentNameReq: null,
                courseNamereq: null,
                /*loading*/
                loading: true,
                /*pageInfo实体*/
                pageInfo: {
                    page: 1,
                    pageSize: 10
                },
                /*course实体*/
                course: {
                    id: null,
                    code: null,
                    year: null,
                    area: null,
                    name: null,
                    type: null,
                    progress: null,
                    courseTime: null,
                    season: null,
                    perTime: null,
                    targetGroup: null,
                    times: null,
                    schoolType: null,
                    perPrice: null,
                    materialPay: null,
                    otherPay: null,
                    startDate: null,
                    endDate: null,
                    description: null,
                    status: null,
                    remark: null
                },
                /*course实体*/
                newCourse: {
                    id: null,
                    code: null,
                    year: null,
                    area: null,
                    name: null,
                    type: null,
                    progress: null,
                    season: null,
                    courseTime: null,
                    perTime: null,
                    targetGroup: null,
                    times: null,
                    schoolType: null,
                    perPrice: null,
                    materialPay: null,
                    otherPay: null,
                    startDate: null,
                    endDate: null,
                    description: null,
                    status: null,
                    remark: null
                },
                /*course实体*/
                courseSeason: {
                    id: null,
                    type: null,
                    value: null,
                    description: null,
                    remark: null
                },
                /*修改验证*/
                ruleModify: {
                    name: [
                        {type: 'string', required: true, message: '输入用户名', trigger: 'blur'}
                    ],
                    loginName: [
                        {type: 'string', required: true, message: '输入登录名', trigger: 'blur'}
                    ],
                    password: [
                        {type: 'string', required: true, message: '输入密码', trigger: 'blur'}
                    ],
                    email: [
                        {required: true, message: '输入邮箱', trigger: 'blur'},
                        {type: 'email', message: '输入正确的邮箱格式', trigger: 'blur'}
                    ]
                },
                /*表显示字段*/
                columns1: [
                    {
                        type: 'selection',
                        width: 60,
                        align: 'center'
                    },
                    {
                        title: '课程编码',
                        key: 'courseCode'
                    },
                    {
                        title: '课程名称',
                        key: 'courseName'
                    },
                    {
                        title: '学生姓名',
                        key: 'studentName'
                    },
                    {
                        title: '报名次数',
                        key: 'times'
                    },
                    {
                        title: '付款状态',
                        key: 'payStatusString'
                    },
                    {
                        title: '报名备注',
                        key: 'remark'
                    },
                    // {
                    //     title: '操作',
                    //     align: 'center',
                    //     key: 'action',
                    //     render: (h, params) => {
                    //         return h('div', [
                    //             h('Button', {
                    //                 props: {
                    //                     type: 'info',
                    //                 },
                    //                 on: {
                    //                     click: () => {
                    //                         this.details(params.row);
                    //                     }
                    //                 }
                    //             }, '详情')
                    //         ]);
                    //     }
                    // },
                ],
                /*表数据*/
                data1: []
            }
        },
        mounted() {
            /*页面初始化调用方法*/
            this.getTable({
                "pageInfo": this.pageInfo
                // "loginName": this.loginName,
            });
        },
        methods: {
            /*pageInfo实体初始化*/
            initPageInfo() {
                this.pageInfo.page = 1;
                this.pageInfo.pageSize = 10;
            },
            /*user实体初始化*/
            initReq() {
                this.studentNameReq = null;
                this.courseNamereq = null;
            },
            /*userNew实体初始化*/
            initNewCourse() {
                this.newCourse.id = null;
                this.newCourse.code = null;
                this.newCourse.year = null;
                this.newCourse.area = null;
                this.newCourse.name = null;
                this.newCourse.type = null;
                this.newCourse.progress = null;
                this.newCourse.season = null;
                this.newCourse.perTime = null;
                this.newCourse.courseTime = null;
                this.newCourse.targetGroup = null;
                this.newCourse.times = null;
                this.newCourse.perPrice = null;
                this.newCourse.materialPay = null;
                this.newCourse.otherPay = null;
                this.newCourse.startDate = null;
                this.newCourse.endDate = null;
                this.newCourse.description = null;
                this.newCourse.remark = null;
            },
            initSelect() {
                this.selectSeason = '';
                this.selectArea = '';
                this.selectSchoolType = '';
                this.selectType = '';
                this.selectProgress = '';
            },
            initUserNew() {
                this.userNew.id = null;
                this.userNew.name = null;
                this.userNew.loginName = null;
                this.userNew.password = null;
                this.userNew.passwordAgain = null;
                this.userNew.email = null;
                this.userNew.tel = null;
            },
            /*userModify实体初始化*/
            initUserModify() {
                this.userModify.id = null;
                this.userModify.name = null;
                this.userModify.loginName = null;
                this.userModify.password = null;
                this.userModify.email = null;
            },
            /*userNew设置*/
            userSet(e) {
                this.user.id = e.id;
                this.user.name = e.name;
                this.user.loginName = e.loginName;
                this.user.password = e.password;
                this.user.email = e.email;
                this.user.passwordAgain = e.passwordAgain;
                this.user.tel = e.tel;
            },
            /*userNew设置*/
            userNewSet(e) {
                this.userNew.id = e.id;
                this.userNew.name = e.name;
                this.userNew.loginName = e.loginName;
                this.userNew.password = e.password;
                this.userNew.passwordAgain = e.password;
                this.userNew.email = e.email;
            },
            /*userModify设置*/
            userModifySet(e) {
                this.userModify.id = e.id;
                this.userModify.name = e.name;
                this.userModify.loginName = e.loginName;
                this.userModify.password = e.password;
                this.userModify.email = e.email;
            },
            /*得到表数据*/
            getTable(e) {
                this.axios({
                    method: 'get',
                    url: '/course/registerInfo',
                    params: {
                        'pageNo': e.pageInfo.page,
                        'pageSize': e.pageInfo.pageSize,
                        'studentName': this.studentNameReq,
                        'courseName': this.courseNamereq
                    }
                }).then(function (response) {
                    this.data1 = response.data.list;
                    this.total = response.data.total;
                    this.initReq();
                }.bind(this)).catch(function (error) {
                    alert(error);
                });
            },
            /*搜索按钮点击事件*/
            search() {
                this.initPageInfo();
                this.getTable({
                    "pageInfo": this.pageInfo
                });
            },
            /*分页点击事件*/
            pageSearch(e) {
                this.pageInfo.page = e;
                this.getTable({
                    "pageInfo": this.pageInfo
                });
            },
            /*新建modal的newOk点击事件*/
            newOk(newCourse) {
                // this.$refs[newCourse].validate((valid) => {
                //     if (valid) {
                //         // this.initUser();
                //         // this.userSet(this.userNew);
                //         console.log(this.newCourse);
                //         this.axios({
                //             method: 'put',
                //             url: '/courseManager/course',
                //             data: this.newCourse
                //         }).then(function (response) {
                //             // this.initUserNew();
                //             this.initNewCourse();
                //             this.getTable({
                //                 "pageInfo": this.pageInfo,
                //                 "loginName": this.loginName
                //             });
                //             this.$Message.info('新建成功');
                //         }.bind(this)).catch(function (error) {
                //             alert(error);
                //         });
                //         this.newModal = false;
                //     } else {
                //         this.$Message.error('表单验证失败!');
                //         setTimeout(() => {
                //             this.loading = false;
                //             this.$nextTick(() => {
                //                 this.loading = true;
                //             });
                //         }, 1000);
                //     }
                // });
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
                                "pageInfo": this.pageInfo
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

            /*点击详情*/
            details(e) {
                // this.detailModal = true;
                // this.data2 = [e];
            },
        }
    }
</script>